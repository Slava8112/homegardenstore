package telran.org.de.scotlandyard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import telran.org.de.scotlandyard.converter.Converter;
import telran.org.de.scotlandyard.dto.PaymentDto;
import telran.org.de.scotlandyard.entity.Payment;
import telran.org.de.scotlandyard.service.PaymentService;

@Slf4j
@RestController
@RequestMapping("v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    private final Converter<Payment,PaymentDto,PaymentDto> converter;

    @PostMapping
    public ResponseEntity<PaymentDto> makePayment(@RequestBody PaymentDto paymentDto) {
        Payment payment = paymentService.makePayment(converter.toEntity(paymentDto));
        return ResponseEntity.ok(converter.toDto(payment));
    }
}
