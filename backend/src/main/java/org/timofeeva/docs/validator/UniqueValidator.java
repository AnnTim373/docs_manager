package org.timofeeva.docs.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private final EntityManager entityManager;

    String fieldName;
    Class<?> entity;

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.entity = constraintAnnotation.entity();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null) return true;
        String query;
        if (o instanceof String) {
            query = "select count(en) from " + entity.getSimpleName() + " en where " + fieldName + " = '" + o + "'";
        } else query = "select count(en) from " + entity.getSimpleName() + " en where " + fieldName + " = " + o;
        Long count = entityManager.createQuery(query, Long.class
        ).getSingleResult();
        return count == 0;
    }

}
