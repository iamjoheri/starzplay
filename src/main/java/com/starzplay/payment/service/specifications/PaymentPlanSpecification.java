package com.starzplay.payment.service.specifications;

import com.starzplay.payment.entity.PaymentPlan;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@AllArgsConstructor
public class PaymentPlanSpecification implements Specification<PaymentPlan> {

    private SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<PaymentPlan> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.greaterThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThan(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return builder.lessThanOrEqualTo(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThan(
                    root.<String> get(criteria.getKey()), criteria.getValue().toString());
        }
        else if (criteria.getOperation().equalsIgnoreCase("=")) {
            return builder.equal(
                    root.<String> get(criteria.getKey()), criteria.getValue());
        }
        else if (criteria.getOperation().equalsIgnoreCase("in")) {
            CriteriaBuilder.In<Long> inclause =builder.in(root.<Long> get(criteria.getKey()));
            for (Long value: (List<Long>)criteria.getValue()) {
                inclause.value(value);
            }
            return  inclause;
            //return builder.in.in(criteria.getKey()).value(criteria.getValue());
//            return builder.in(
//                    root.<String> get(criteria.getKey()), criteria.getValue());
        }
        else if (criteria.getOperation().equalsIgnoreCase("like")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.<String>get(criteria.getKey()), (String)criteria.getValue());
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }

        return null;
    }
}
