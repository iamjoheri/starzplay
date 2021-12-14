package com.starzplay.payment.service.implementation;

import com.starzplay.payment.dto.methods.PaymentMethodDto;
import com.starzplay.payment.dto.plan.PaymentPlanDto;
import com.starzplay.payment.entity.PaymentMethod;
import com.starzplay.payment.entity.PaymentPlan;
import com.starzplay.payment.mapper.PaymentMethodObjectMapper;
import com.starzplay.payment.mapper.PaymentPlanObjectMapper;
import com.starzplay.payment.repository.PaymentMethodRepository;
import com.starzplay.payment.repository.PaymentPlanRepository;
import com.starzplay.payment.service.interfaces.IPaymentMethodService;
import com.starzplay.payment.service.specifications.builder.PaymentMethodSpecificationBuilder;
import com.starzplay.payment.service.specifications.builder.PaymentPlanSpecificationBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements IPaymentMethodService {

    private final PaymentMethodObjectMapper paymentMethodObjectMapper;
    private final PaymentMethodRepository paymentMethodRepository;

    private final PaymentPlanObjectMapper paymentPlanObjectMapper;
    private final PaymentPlanRepository paymentPlanRepository;

    @Override
    @Transactional
    public PaymentMethodDto createPaymentMethod(PaymentMethodDto aPaymentMethodDto) {

        PaymentMethod paymentMethod = paymentMethodObjectMapper.toEntity(aPaymentMethodDto);
        paymentMethod = paymentMethodRepository.saveAndFlush(paymentMethod);

        /*************Save Payment Plan ***********************/

        List<PaymentPlan> paymentPlanList = new ArrayList<>();
        List<PaymentPlanDto> paymentPlanDtoList = aPaymentMethodDto.getPaymentPlansDtoList();
        if(paymentPlanDtoList != null && paymentPlanDtoList.size() > 0){

            for (PaymentPlanDto paymentPlanDto : paymentPlanDtoList) {
                paymentPlanDto.setPaymentMethodId(paymentMethod.getPaymentMethodId());
                PaymentPlan paymentPlan =  paymentPlanObjectMapper.toEntity(paymentPlanDto);
                paymentPlanList.add(paymentPlan);
            }
            paymentPlanRepository.saveAll(paymentPlanList);
        }

        return paymentMethodObjectMapper.toDto(paymentMethod);

    }


    @Override
    public List<PaymentMethodDto> paymentMethodList() {
        List<PaymentMethodDto> paymentMethodDtoList = null;
        try {
            List<PaymentMethod> paymentMethodList = paymentMethodRepository.findAll();
            if(paymentMethodList != null && paymentMethodList.size() > 0){

                paymentMethodDtoList = new ArrayList<PaymentMethodDto>();
                for (PaymentMethod paymentMethod : paymentMethodList) {
                    if(paymentMethod != null) {
                        PaymentMethodDto paymentMethodDto = paymentMethodObjectMapper.toDto(paymentMethod);
                        paymentMethodDto.setPaymentPlansDtoList(new ArrayList<PaymentPlanDto>());
                        // // Payment Plan
                        PaymentPlanSpecificationBuilder paymentPlanSpecificationBuilder = new PaymentPlanSpecificationBuilder();
                        paymentPlanSpecificationBuilder.with("paymentMethodId","=",paymentMethod.getPaymentMethodId(),false);
                        List<PaymentPlan> paymentPlanList = paymentPlanRepository.findAll(paymentPlanSpecificationBuilder.build());
                        for (PaymentPlan paymentPlan: paymentPlanList) {
                            paymentMethodDto.getPaymentPlansDtoList().add(paymentPlanObjectMapper.toDto(paymentPlan));
                        }
                        paymentMethodDtoList.add(paymentMethodDto);
                    }
                }
            }
            return paymentMethodDtoList;
        }
        catch (EntityNotFoundException e){
            return null;
        }catch (Exception ex){
            log.info("PaymentMethodRegistrationDto ::: "+ex);
            return null;
        }
    }

    @Override
    @Transactional
    public PaymentMethodDto getPaymentMethodById(Long aPaymentMethodId) {

        PaymentMethod paymentMethod = null;
        try {
            paymentMethod = paymentMethodRepository.findById(aPaymentMethodId).get();
        }
        catch (EntityNotFoundException e){
            log.info("Payment Method not found with id ::: "+aPaymentMethodId);
            return null;
        }catch (Exception ex){
            log.info("Payment Method not found with id ::: "+aPaymentMethodId);
            log.info("PaymentMethodRegistrationDto ::: "+ex);
            return null;
        }

        if(paymentMethod != null) {

            PaymentMethodDto paymentMethodDto = paymentMethodObjectMapper.toDto(paymentMethod);
            paymentMethodDto.setPaymentPlansDtoList(new ArrayList<PaymentPlanDto>());

            // Payment Plan
            PaymentPlanSpecificationBuilder paymentPlanSpecificationBuilder = new PaymentPlanSpecificationBuilder();
            paymentPlanSpecificationBuilder.with("paymentMethodId","=",paymentMethod.getPaymentMethodId(),false);
            List<PaymentPlan> paymentPlanList = paymentPlanRepository.findAll(paymentPlanSpecificationBuilder.build());
            for (PaymentPlan paymentPlan: paymentPlanList) {
                paymentMethodDto.getPaymentPlansDtoList().add(paymentPlanObjectMapper.toDto(paymentPlan));
            }
            return paymentMethodDto;
        }else {
            return null;
        }
    }


    @Override
    @Transactional
    public List<PaymentMethodDto> getPaymentMethodByName(String aName) {
        List<PaymentMethodDto> paymentMethodDtoList = null;

        PaymentMethodSpecificationBuilder paymentMethodSpecificationBuilder = new PaymentMethodSpecificationBuilder();
        paymentMethodSpecificationBuilder.with("name","like","%"+aName+"%" ,false);
        List<PaymentMethod> paymentMethodList = paymentMethodRepository.findAll(paymentMethodSpecificationBuilder.build());

        if(paymentMethodList != null && paymentMethodList.size() > 0){
            paymentMethodDtoList = new ArrayList<PaymentMethodDto>();
            for (PaymentMethod paymentMethod : paymentMethodList) {
                if(paymentMethod != null) {
                    PaymentMethodDto paymentMethodDto = paymentMethodObjectMapper.toDto(paymentMethod);
                    paymentMethodDto.setPaymentPlansDtoList(new ArrayList<PaymentPlanDto>());
                    // // Payment Plan
                    PaymentPlanSpecificationBuilder paymentPlanSpecificationBuilder = new PaymentPlanSpecificationBuilder();
                    paymentPlanSpecificationBuilder.with("paymentMethodId","=",paymentMethod.getPaymentMethodId(),false);
                    List<PaymentPlan> paymentPlanList = paymentPlanRepository.findAll(paymentPlanSpecificationBuilder.build());
                    for (PaymentPlan paymentPlan: paymentPlanList) {
                        paymentMethodDto.getPaymentPlansDtoList().add(paymentPlanObjectMapper.toDto(paymentPlan));
                    }
                    paymentMethodDtoList.add(paymentMethodDto);
                }
            }
        }

        return paymentMethodDtoList;
    }

    @Override
    public PaymentMethodDto updatePaymentMethod(Long paymentMethodId, PaymentMethodDto aPaymentMethodDto) {

        if (paymentMethodRepository.findById(paymentMethodId).isPresent()){
            PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodId).get();
            paymentMethod.setDisplayName(aPaymentMethodDto.getDisplayName());
            paymentMethod.setName(aPaymentMethodDto.getName());
            paymentMethod.setPaymentType(aPaymentMethodDto.getPaymentType());

            paymentMethod = paymentMethodRepository.saveAndFlush(paymentMethod);

            List<PaymentPlan> paymentPlanList = new ArrayList<>();
            List<PaymentPlanDto> paymentPlanDtoList = aPaymentMethodDto.getPaymentPlansDtoList();
            if(paymentPlanDtoList != null && paymentPlanDtoList.size() > 0){

                for (PaymentPlanDto licenseTypeDto : paymentPlanDtoList) {
                    licenseTypeDto.setPaymentMethodId(paymentMethod.getPaymentMethodId());
                    PaymentPlan paymentPlan =  paymentPlanObjectMapper.toEntity(licenseTypeDto);
                    paymentPlanList.add(paymentPlan);
                }
            }
            paymentPlanRepository.saveAll(paymentPlanList);
            return paymentMethodObjectMapper.toDto(paymentMethod);
        }else{
            return null;
        }
    }
}
