package telran.org.de.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import telran.org.de.scotlandyard.entity.Order;
import telran.org.de.scotlandyard.service.ReportService;

import java.util.List;

@RestController
@RequestMapping("/v1/reports")
@RequiredArgsConstructor
public class ReportController {
    private final ReportService reportService;

    @GetMapping("/топ-10-купленных-товаров")
    public ResponseEntity<List<Object[]>> getTop10PurchasedProducts() {
        List<Object[]> result = reportService.getTop10PurchasedProducts();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/топ-10-отмененных-товаров")
    public ResponseEntity<List<Object[]>> getTop10CancelledProducts() {
        List<Object[]> result = reportService.getTop10CancelledProducts();
        return ResponseEntity.ok(result);
    }

    @GetMapping("/прибыль-с-группировкой")
    public ResponseEntity<List<Object[]>> getProfitGroupedByPeriod(@RequestParam String period) {
        List<Object[]> result = reportService.getProfitGroupedByPeriod(period);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/товары-ожидающие-оплаты")
    public ResponseEntity<List<Order>> getAwaitingPaymentOrders(@RequestParam int days) {
        List<Order> result = reportService.getAwaitingPaymentOrdersForNDays(days);
        return ResponseEntity.ok(result);
    }
}
