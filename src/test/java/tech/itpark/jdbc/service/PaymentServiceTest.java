package tech.itpark.jdbc.service;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentServiceTest {
  @Test
  void shouldCalculateForSinglePayment() {
    // A-A-A
    // Arrange
    final var service = new PaymentService();
    final var price = 5;
    final var months = 1;
    final var expected = List.of(5);

    // Act
    final var actual = service.calculate(price, months);

    // Assert
    assertEquals(expected, actual);
  }
}