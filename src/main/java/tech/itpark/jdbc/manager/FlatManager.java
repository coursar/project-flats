package tech.itpark.jdbc.manager;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import tech.itpark.jdbc.mapper.FlatRowMapper;
import tech.itpark.jdbc.model.Flat;
import tech.itpark.jdbc.service.PaymentService;

import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class FlatManager {
  private final NamedParameterJdbcTemplate template;
  private final PaymentService paymentService;
  private final FlatRowMapper rowMapper;

  public List<Flat> getAll() {
    return template.query(
        "SELECT id, owner_id, district, price, rooms FROM flats ORDER BY id LIMIT 50",
        rowMapper
    );
  }

  public Flat getById(long id) {
    return template.queryForObject(
        "SELECT id, owner_id, district, price, rooms FROM flats WHERE id = :id",
        Map.of("id", id),
        rowMapper
    );
  }

  public List<Flat> search(String district, int rooms) {
    return template.query(
        "SELECT id, owner_id, district, price, rooms FROM flats WHERE district = :district AND rooms = :rooms",
        Map.of("district", district, "rooms", rooms),
        rowMapper
    );
  }

  public List<Flat> getByOwnerId(long ownerId) {
    return template.query(
        "SELECT id, owner_id, district, price, rooms FROM flats WHERE owner_id = :owner_id",
        Map.of("owner_id", ownerId),
        rowMapper
    );
  }

  public List<Integer> calculatePaymentsForId(long id, int months) {
    // TODO: по id достаёте квартиру
    // TODO: берёте стоимость и делите по месяцам
    final var flat = getById(id);
    return paymentService.calculate(flat.getPrice(), months);
  }

  public Flat save(Flat item) {
    if (item.getId() == 0) {
      KeyHolder keyHolder = new GeneratedKeyHolder();
      template.update(
          "INSERT INTO flats(owner_id, district, price, rooms) VALUES (:owner_id, :district, :price, :rooms)",
          new MapSqlParameterSource(Map.of(
              "owner_id", item.getOwnerId(),
              "district", item.getDistrict(),
              "price", item.getPrice(),
              "rooms", item.getRooms()
          )),
          keyHolder
      );
      long id = keyHolder.getKey().longValue();
      return getById(id);
    }

    template.update(
        "UPDATE flats SET owner_id = :owner_id, district = :district, price = :price, rooms = :rooms WHERE id = :id",
        Map.of(
            "id", item.getId(),
            "owner_id", item.getOwnerId(),
            "district", item.getDistrict(),
            "price", item.getPrice(),
            "rooms", item.getRooms()
        )
    );

    return getById(item.getId());
  }

  public Flat removeById(long id) {
    Flat item = getById(id);

    template.update(
        "DELETE FROM flats WHERE id = :id",
        Map.of("id", item.getId())
    );

    return item;
  }
}
