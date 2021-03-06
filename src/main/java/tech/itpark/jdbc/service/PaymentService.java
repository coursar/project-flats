package tech.itpark.jdbc.service;

import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

// TODO: Ctrl + Shift + T
@Component
public class PaymentService {
    // price - в миллионах
    // ежемесячные платежи тоже в миллионах
    public List<Integer> calculate(int price, int months) {
        int payment = price / months;
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < months - 1; i++) {
            result.add(payment);
        }
        // price - 5
        // month - 4
        // должно получиться 2
        result.add(price - payment * (months - 1));
        return result;
//        return Stream.generate(() -> payment)
//                .limit(months)
//                .collect(Collectors.toList());
    }
}
