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
@Table(name = "st_payment_plan")
@Accessors(chain=true)
public class PaymentPlan {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "paymentPlanId")
    private Long id;

    @Column(name = "NetAmount")
    private Double netAmount;

    @Column(name = "TaxAmount")
    private Double taxAmount;

    @Column(name = "GrossAmount")
    private Double grossAmount;

    @Column(name = "Currency")
    private String currency;

    @Column(name = "Duration")
    private String duration;

    @Column(name = "PaymentMethodId")
    private Long paymentMethodId;
}
