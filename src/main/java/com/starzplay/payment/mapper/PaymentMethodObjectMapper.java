package com.starzplay.payment.mapper;

import com.starzplay.payment.dto.methods.PaymentMethodDto;
import com.starzplay.payment.entity.PaymentMethod;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PaymentMethodObjectMapper {

    PaymentMethod toEntity(PaymentMethodDto dto);
    PaymentMethodDto toDto(PaymentMethod entity);
}
