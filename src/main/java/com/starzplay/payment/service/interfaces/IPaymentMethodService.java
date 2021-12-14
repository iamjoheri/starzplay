package com.starzplay.payment.service.interfaces;

import com.starzplay.payment.dto.methods.PaymentMethodDto;

import java.util.List;

public interface IPaymentMethodService {

    PaymentMethodDto createPaymentMethod(PaymentMethodDto aPaymentMethodDto);
    PaymentMethodDto getPaymentMethodById(Long aPaymentMethodId);
    List<PaymentMethodDto> getPaymentMethodByName(String aName);
    List<PaymentMethodDto> paymentMethodList();
    PaymentMethodDto updatePaymentMethod(Long paymentMethodId,PaymentMethodDto aPaymentMethodDto);
}
