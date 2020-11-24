package tech.itpark.jdbc.mapper;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import tech.itpark.jdbc.model.Flat;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class FlatRowMapper implements RowMapper<Flat> {
  public Flat mapRow(ResultSet rs, int rowNum) throws SQLException {
    return new Flat(
        rs.getLong("id"),
        rs.getLong("owner_id"),
        rs.getString("district"),
        rs.getInt("price"),
        rs.getInt("rooms")
    );
  }
}
