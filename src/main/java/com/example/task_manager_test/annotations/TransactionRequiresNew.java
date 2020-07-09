package com.example.task_manager_test.annotations;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
public @interface TransactionRequiresNew {
}
