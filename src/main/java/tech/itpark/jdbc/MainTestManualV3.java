package tech.itpark.jdbc;

import tech.itpark.jdbc.service.PaymentService;

import java.util.List;

// Важно: так делать не нужно! Мы просто разбирали по шагам, что на самом деле происходит
public class MainTestManualV3 {
    public static void main(String[] args) {
        shouldCalculateForOnePayment();
        shouldCalculateForExactPayments();
    }

    // TODO: Тестирование: сравнение ожидаемого и фактического результата
    public static void shouldCalculateForOnePayment() {
        // A-A-A
        // TODO: A - Arrange (подготовка данных)
        final var service = new PaymentService();
        final var price = 5;
        final var months = 1;
        final var expected = List.of(5); // список из 1 элемента = 5
        // TODO: A - Act (выполнение целевого действия)
        final var actual = service.calculate(price, months);
        // TODO: A - Assert (сравнение ожидаемого и фактического)
        if (!expected.equals(actual)) {
            throw new RuntimeException();
        }
    }

    public static void shouldCalculateForExactPayments() {
        final var service = new PaymentService();
        final var actual = service.calculate(5, 5);
        // TODO: Тестирование: сравнение ожидаемого и фактического результата
        final var expected = List.of(1, 1, 1, 1, 1); // список из 1 элемента = 5
        if (!expected.equals(actual)) {
            throw new RuntimeException();
        }
    }
}
