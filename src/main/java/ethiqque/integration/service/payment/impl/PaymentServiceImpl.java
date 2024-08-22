package ethiqque.integration.service.payment.impl;

import ethiqque.integration.entity.dto.PaymentRequestDto;
import ethiqque.integration.entity.dto.PaymentResponseDto;
import ethiqque.integration.entity.model.Payment;
import ethiqque.integration.repository.PaymentRepository;
import ethiqque.integration.service.payment.PaymentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Value("${applepay.merchantId}")
    private String appleMerchantId;

    @Value("${applepay.keyId}")
    private String appleKeyId;

    @Value("${applepay.privateKey}")
    private String applePrivateKey;

    @Value("${googlepay.merchantId}")
    private String googleMerchantId;

    @Value("${googlepay.merchantName}")
    private String googleMerchantName;

    @Value("${samsungpay.merchantId}")
    private String samsungMerchantId;

    @Value("${samsungpay.serviceId}")
    private String samsungServiceId;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public PaymentResponseDto processApplePayPayment(PaymentRequestDto request) {
        return processPayment(request, appleMerchantId, "Apple Pay");
    }

    @Override
    @Transactional
    public PaymentResponseDto processGooglePayPayment(PaymentRequestDto request) {
        return processPayment(request, googleMerchantId, "Google Pay");
    }

    @Override
    @Transactional
    public PaymentResponseDto processSamsungPayPayment(PaymentRequestDto request) {
        return processPayment(request, samsungMerchantId, "Samsung Pay");
    }

    private PaymentResponseDto processPayment(PaymentRequestDto request, String merchantId, String paymentMethod) {
        boolean isValidToken = validatePaymentToken(request.getPaymentToken(), paymentMethod);

        Payment payment = new Payment();
        payment.setMerchantId(merchantId);
        payment.setPaymentToken(request.getPaymentToken());
        payment.setAmount(new BigDecimal(request.getAmount()));
        payment.setCurrency(request.getCurrency());
        payment.setStatus(isValidToken ? "SUCCESS" : "FAILED");

        Payment savedPayment = paymentRepository.save(payment);

        return PaymentResponseDto.builder()
                .paymentId(savedPayment.getId())
                .status(savedPayment.getStatus())
                .build();
    }

    private boolean validatePaymentToken(String token, String paymentMethod) {
        return true;
    }
}

