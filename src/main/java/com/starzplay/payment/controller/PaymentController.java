package com.starzplay.payment.controller;

import com.starzplay.payment.dto.methods.PaymentMethodDto;
import com.starzplay.payment.enums.Status;
import com.starzplay.payment.model.Response;
import com.starzplay.payment.service.interfaces.IPaymentMethodService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1.0/configuration")
@AllArgsConstructor
public class PaymentController {

    private final IPaymentMethodService paymentMethodService;

//    @GetMapping(path = "/payment-methods")
    @GetMapping(value = "/payment-methods/all")
    public ResponseEntity<?> allPaymentMethods() throws Exception {

        Response response = new Response();
        List<PaymentMethodDto> paymentMethodRegistrationDtoList = paymentMethodService.paymentMethodList();

        if (paymentMethodRegistrationDtoList != null && paymentMethodRegistrationDtoList.size() > 0) {
            response.setPaymentMethods(paymentMethodRegistrationDtoList);
            response.setMessage("Payment get successfully");
            response.setStatus(Status.SUCCESS);
        } else {
            response.setMessage("Payment Method couldn't be loaded. Please try again later.");
            response.setStatus(Status.ERROR);
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/payment-methods")
    public ResponseEntity<?> getPaymentMethod(@RequestParam(value = "id",required=false) Long paymentId, @RequestParam(value = "name",required=false) String name) throws Exception{

        Response response = new Response();
        if(paymentId != null && paymentId > 0L){
            PaymentMethodDto paymentMethodDto = paymentMethodService.getPaymentMethodById(paymentId);

            if(paymentMethodDto != null ) {
                response.setPaymentMethods(paymentMethodDto);
                response.setMessage("Payment Method found with ID.");
                response.setStatus(Status.SUCCESS);
            }
            else
            {
                response.setMessage("Payment Method couldn't be found. Please try again later.");
                response.setStatus(Status.ERROR);
            }
        }else if(name != null && !name.isEmpty()){
            List<PaymentMethodDto> paymentMethodDtoList  = paymentMethodService.getPaymentMethodByName(name);
            if(paymentMethodDtoList != null && paymentMethodDtoList.size() > 0 ) {
                response.setPaymentMethods(paymentMethodDtoList);
                response.setMessage("Payment Method found with name.");
                response.setStatus(Status.SUCCESS);
            }
            else
            {
                response.setMessage("Payment Method couldn't be found. Please try again later.");
                response.setStatus(Status.ERROR);
            }

            return ResponseEntity.ok(response);
        }

        response.setMessage("Payment Method couldn't be found. Please try again later.");
        response.setStatus(Status.ERROR);
        return ResponseEntity.ok(response);
    }
    @PostMapping(path = "/payment-methods",consumes = {MediaType.APPLICATION_JSON_VALUE},produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> create(@RequestBody PaymentMethodDto aPaymentMethodDto) throws Exception {

        Response response = new Response();
        PaymentMethodDto paymentMethod = paymentMethodService.createPaymentMethod(aPaymentMethodDto);

        if (paymentMethod != null && paymentMethod.getPaymentMethodId() > 0) {
            response.setPaymentMethods(paymentMethod);
            response.setMessage("Payment has been added successfully");
            response.setStatus(Status.SUCCESS);
        } else {
            response.setMessage("Payment Method couldn't be added. Please try again later.");
            response.setStatus(Status.ERROR);
        }
        return ResponseEntity.ok(response);
    }
    @PutMapping(path="/payment-methods/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePaymentMethod(@PathVariable(name = "id") Long paymentMethodId,@RequestBody PaymentMethodDto aPaymentMethodDto) throws Exception{
        Response response = new Response();

        PaymentMethodDto paymentMethod = paymentMethodService.updatePaymentMethod(paymentMethodId,aPaymentMethodDto);
        if (paymentMethod != null && paymentMethod.getPaymentMethodId() > 0) {
            response.setPaymentMethods(paymentMethod);
            response.setMessage("Payment has been updated successfully");
            response.setStatus(Status.SUCCESS);
        } else {
            response.setMessage("Payment Method couldn't be updated. Please try again later.");
            response.setStatus(Status.ERROR);
        }
        return ResponseEntity.ok(response);
    }
}
