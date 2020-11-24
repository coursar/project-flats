package tech.itpark.jdbc.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tech.itpark.jdbc.manager.FlatManager;
import tech.itpark.jdbc.model.Flat;

import java.util.List;

@RestController
@RequestMapping("/flats")
@RequiredArgsConstructor
public class FlatController {
  private final FlatManager manager;

  @GetMapping
  public List<Flat> getAll() {
    return manager.getAll();
  }

  @GetMapping("/{id}")
  public Flat getById(@PathVariable long id) {
    return manager.getById(id);
  }

  @GetMapping("/search")
  public List<Flat> search(@RequestParam String district, @RequestParam int rooms) {
    return manager.search(district, rooms);
  }

  @GetMapping("/by-owner/{ownerId}")
  public List<Flat> getByOwnerId(@PathVariable long ownerId) {
    return manager.getByOwnerId(ownerId);
  }

  @GetMapping("/{id}/payments")
  public List<Integer> getPayments(@PathVariable long id, @RequestParam int months) {
    return manager.calculatePaymentsForId(id, months);
  }


  @PostMapping
  public Flat save(@RequestBody Flat item) {
    return manager.save(item);
  }

  @DeleteMapping("/{id}")
  public Flat removeById(@PathVariable long id) {
    return manager.removeById(id);
  }
}
