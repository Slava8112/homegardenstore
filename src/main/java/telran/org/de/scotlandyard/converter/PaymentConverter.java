package telran.org.de.scotlandyard.converter;

import org.springframework.stereotype.Component;
import telran.org.de.scotlandyard.dto.PaymentDto;
import telran.org.de.scotlandyard.entity.Payment;

@Component
public class PaymentConverter implements Converter<Payment, PaymentDto, PaymentDto> {

    @Override
    public Payment toEntity(PaymentDto paymentDto) {
        return new Payment(null, paymentDto.orderId(), paymentDto.amount());
    }

    @Override
    public PaymentDto toDto(Payment payment) {
        return new PaymentDto(payment.getId(),
                payment.getOrderId(), payment.getAmount());
    }
}
