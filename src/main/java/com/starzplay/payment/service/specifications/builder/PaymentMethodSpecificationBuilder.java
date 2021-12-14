package com.starzplay.payment.service.specifications.builder;

import com.starzplay.payment.entity.PaymentMethod;
import com.starzplay.payment.service.specifications.PaymentMethodSpecification;
import com.starzplay.payment.service.specifications.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PaymentMethodSpecificationBuilder {
    private final List<SearchCriteria> params;

    public PaymentMethodSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public PaymentMethodSpecificationBuilder with(String key, String operation, Object value, boolean orPredicate) {
        params.add(new SearchCriteria(key, operation, value,orPredicate));
        return this;
    }

    public Specification<PaymentMethod> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(PaymentMethodSpecification::new)
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
