package telran.org.de.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import telran.org.de.scotlandyard.entity.Order;
import telran.org.de.scotlandyard.entity.UserEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByUserEntity(UserEntity userEntity);

    // Топ 10 купленных товаров
    @Query("SELECT oi.product, COUNT(oi) FROM OrderItem oi GROUP BY oi.product ORDER BY COUNT(oi) DESC")
    List<Object[]> findTop10PurchasedProducts();

    // Топ 10 отменённых товаров
    @Query("SELECT oi.product, COUNT(oi) FROM OrderItem oi JOIN oi.order o WHERE o.status = 'CANCELED' GROUP BY oi.product ORDER BY COUNT(oi) DESC")
    List<Object[]> findTop10CancelledProducts();

    // Товары в статусе "Ожидает оплаты" более N дней
    @Query("SELECT o FROM Order o WHERE o.status = 'AWAIT_PAYMENT' AND o.createdAT <= :thresholdDate")
    List<Order> findOrdersAwaitingPaymentSince(LocalDateTime thresholdDate);

    // Прибыль с группировкой по периоду
    @Query("SELECT SUM(o.totalPrice), DATE_FORMAT(o.createdAT, :period) as period_group FROM Order o GROUP BY period_group ORDER BY period_group")
    List<Object[]> findProfitGroupedByPeriod(String period);

}
