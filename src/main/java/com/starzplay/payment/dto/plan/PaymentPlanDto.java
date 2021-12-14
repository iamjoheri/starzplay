package com.starzplay.payment.dto.plan;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentPlanDto {

    private Long id;
    private Double netAmount;
    private Double taxAmount;
    private Double grossAmount;
    private String currency;
    private String duration;
    @JsonIgnore
    private Long paymentMethodId;
}
