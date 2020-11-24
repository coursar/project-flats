package tech.itpark.jdbc.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentServiceTest {
  @Test
  void shouldCalculateSinglePayment() {
    // A-A-A
    // Arrange
    final var service = new PaymentService();
    final var price = 5;
    final var months = 1;
    final var expected = List.of(5);
    final var actual = service.calculate(price, months);

    assertEquals(expected, actual);
  }
}