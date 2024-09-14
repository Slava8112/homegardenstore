package telran.org.de.scotlandyard.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import telran.org.de.scotlandyard.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
