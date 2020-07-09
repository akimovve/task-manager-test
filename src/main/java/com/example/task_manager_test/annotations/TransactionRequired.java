package com.example.task_manager_test.annotations;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE}) // что именно может быть помечено этой аннотацией
@Retention(RetentionPolicy.RUNTIME) // жизненный цикл аннотации
@Documented // аннотация должна быть добавлена в javadoc поля/метода и т.д.
@Inherited // аннотация может быть унаследована потомком класса, отмеченного такой аннотацией
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
public @interface TransactionRequired {
}
