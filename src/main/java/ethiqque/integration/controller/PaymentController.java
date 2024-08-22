package ethiqque.integration.controller;

import ethiqque.integration.entity.dto.PaymentRequestDto;
import ethiqque.integration.entity.dto.PaymentResponseDto;
import ethiqque.integration.service.payment.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/applepay")
    public ResponseEntity<PaymentResponseDto> processApplePayPayment(@RequestBody PaymentRequestDto request) {
        PaymentResponseDto response = paymentService.processApplePayPayment(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/googlepay")
    public ResponseEntity<PaymentResponseDto> processGooglePayPayment(@RequestBody PaymentRequestDto request) {
        PaymentResponseDto response = paymentService.processGooglePayPayment(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/samsungpay")
    public ResponseEntity<PaymentResponseDto> processSamsungPayPayment(@RequestBody PaymentRequestDto request) {
        PaymentResponseDto response = paymentService.processSamsungPayPayment(request);
        return ResponseEntity.ok(response);
    }
}
