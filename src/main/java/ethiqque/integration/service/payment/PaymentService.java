package ethiqque.integration.service.payment;


import ethiqque.integration.entity.dto.PaymentRequestDto;
import ethiqque.integration.entity.dto.PaymentResponseDto;

public interface PaymentService {
    PaymentResponseDto processApplePayPayment(PaymentRequestDto request);
    PaymentResponseDto processGooglePayPayment(PaymentRequestDto request);
    PaymentResponseDto processSamsungPayPayment(PaymentRequestDto request); // New method
}
