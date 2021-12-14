package com.starzplay.payment.dto.methods;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.starzplay.payment.dto.plan.PaymentPlanDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class PaymentMethodDto {

    @JsonIgnore
    private Long paymentMethodId;
    private String displayName;
    private String paymentType;
    private String name;
    @JsonProperty("paymentPlans")
    private List<PaymentPlanDto> paymentPlansDtoList;
}
