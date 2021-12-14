package com.starzplay.payment.repository;

import com.starzplay.payment.entity.PaymentMethod;
import com.starzplay.payment.entity.PaymentPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentPlanRepository extends JpaRepository<PaymentPlan, Long>, JpaSpecificationExecutor<PaymentPlan> {

}
