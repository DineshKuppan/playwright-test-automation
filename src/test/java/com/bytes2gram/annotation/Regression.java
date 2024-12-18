package com.bytes2gram.annotation;

import java.lang.annotation.*;
import org.junit.jupiter.api.Tag;

/**
 * @author Dinesh
 */
@Documented
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Tag("regression")
public @interface Regression {}
