package org.timofeeva.docs.util;

import com.querydsl.core.types.dsl.EntityPathBase;
import com.querydsl.core.types.dsl.StringExpression;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.querydsl.binding.SingleValueBinding;

public class MyBinderCustomizer<T extends EntityPathBase<?>> implements QuerydslBinderCustomizer<T> {

    @Override
    @SuppressWarnings("NullableProblems")
    public void customize(QuerydslBindings var1, T var2) {
        var1.bind(String.class).first((SingleValueBinding<StringPath, String>) StringExpression::containsIgnoreCase);
    }

}
