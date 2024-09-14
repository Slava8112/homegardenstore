package telran.org.de.scotlandyard.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import telran.org.de.scotlandyard.entity.Order;
import telran.org.de.scotlandyard.entity.Payment;
import telran.org.de.scotlandyard.exception.PaymentAmountNotEnoughException;
import telran.org.de.scotlandyard.model.Status;
import telran.org.de.scotlandyard.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private OrderService orderService;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    @Transactional
    public Payment makePayment(Payment payment) {
        Long orderId = payment.getOrderId();
        Order order = orderService.findById(orderId);
        if (order.getTotalPrice() > payment.getAmount()) {
            throw new PaymentAmountNotEnoughException("Not enough money to pay order");
        }
        Payment paymentEntity = paymentRepository.save(payment);
        orderService.changeStatus(order.getId(), Status.PAID);
        return paymentEntity;
    }
}
