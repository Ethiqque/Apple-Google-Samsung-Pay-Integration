package ethiqque.integration.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PaymentResponseDto {
    private Long paymentId;
    private String status;
}
