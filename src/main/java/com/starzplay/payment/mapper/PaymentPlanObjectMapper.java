package com.starzplay.payment.mapper;

import com.starzplay.payment.dto.plan.PaymentPlanDto;
import com.starzplay.payment.entity.PaymentPlan;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentPlanObjectMapper {

    PaymentPlan toEntity(PaymentPlanDto dto);
    PaymentPlanDto toDto(PaymentPlan entity);
}
