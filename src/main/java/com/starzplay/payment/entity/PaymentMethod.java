package com.starzplay.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Table(name = "st_payment_method")
@Accessors(chain=true)
public class PaymentMethod {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PaymentMethodId")
    private Long paymentMethodId;

    @Column(name = "name")
    private String name;

    @Column(name = "DisplayName")
    private String displayName;

    @Column(name = "PaymentType")
    private String paymentType;

}
