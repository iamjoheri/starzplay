package com.starzplay.payment.service.specifications.builder;

import com.starzplay.payment.entity.PaymentPlan;
import com.starzplay.payment.service.specifications.PaymentPlanSpecification;
import com.starzplay.payment.service.specifications.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentPlanSpecificationBuilder {
    private final List<SearchCriteria> params;

    public PaymentPlanSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public PaymentPlanSpecificationBuilder with(String key, String operation, Object value, boolean orPredicate) {
        params.add(new SearchCriteria(key, operation, value,orPredicate));
        return this;
    }

    public Specification<PaymentPlan> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(PaymentPlanSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i)
                    .isOrPredicate()
                    ? Specification.where(result)
                    .or(specs.get(i))
                    : Specification.where(result)
                    .and(specs.get(i));
        }
        return result;
    }
}
