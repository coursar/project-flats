package tech.itpark.jdbc;

import tech.itpark.jdbc.service.PaymentService;

// Важно: так делать не нужно! Мы просто разбирали по шагам, что на самом деле происходит
public class MainTestManualV1 {
    public static void main(String[] args) {
        shouldCalculateForOnePayment();
        shouldCalculateForExactPayments();
    }

    public static void shouldCalculateForOnePayment() {
        final var service = new PaymentService();
        final var actual = service.calculate(5, 1);
        // TODO: Тестирование: сравнение ожидаемого и фактического результата
        System.out.println(actual);
    }

    public static void shouldCalculateForExactPayments() {
        final var service = new PaymentService();
        final var actual = service.calculate(5, 5);
        // TODO: Тестирование: сравнение ожидаемого и фактического результата
        System.out.println(actual);
    }
}
