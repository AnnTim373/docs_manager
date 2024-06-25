package org.timofeeva.docs.validator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
@RequiredArgsConstructor
public class ReferenceValidator implements ConstraintValidator<Reference, Object> {

    private final EntityManager entityManager;

    private Class<?> entity;
    private String fieldName;

    @Override
    public void initialize(Reference constraintAnnotation) {
        this.entity = constraintAnnotation.entity();
        this.fieldName = constraintAnnotation.fieldName();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        if (o == null) return true;
        Long count = entityManager.createQuery(
                "select count(en) from " + entity.getSimpleName() +
                        " en where " + fieldName + " = " + o, Long.class
        ).getSingleResult();
        return count != 0;
    }

}
