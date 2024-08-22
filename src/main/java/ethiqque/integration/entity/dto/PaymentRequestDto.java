package ethiqque.integration.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentRequestDto {
    private String paymentToken;
    private String amount;
    private String currency;
}