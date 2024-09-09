package telran.org.de.scotlandyard.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.entity.Order;
import telran.org.de.scotlandyard.repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor

public class ReportService {
    private final OrderRepository orderRepository;

    // Топ 10 купленных товаров
    public List<Object[]> getTop10PurchasedProducts() {
        return orderRepository.findTop10PurchasedProducts();
    }

    // Топ 10 часто отменяемых товаров
    public List<Object[]> getTop10CancelledProducts() {
        return orderRepository.findTop10CancelledProducts();
    }

    // Список товаров, которые находятся в статусе "Ожидает оплаты" более N дней
    public List<Order> getAwaitingPaymentOrdersForNDays(int days) {
        LocalDateTime thresholdDate = LocalDateTime.now().minusDays(days);
        return orderRepository.findOrdersAwaitingPaymentSince(thresholdDate);
    }

    // Прибыль за N дней, месяцев, лет с группировкой по часам, дням, неделям, месяцам
    public List<Object[]> getProfitGroupedByPeriod(String period) {
        return orderRepository.findProfitGroupedByPeriod(period);
    }
}
