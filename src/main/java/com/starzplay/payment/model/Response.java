package com.starzplay.payment.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.starzplay.payment.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private Status status;
    private Object paymentMethods;
    private String message;
    private Map<String,Object> promotedFields;

}
