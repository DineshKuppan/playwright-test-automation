package com.bytes2gram.annotation;

import java.lang.annotation.*;
import org.junit.jupiter.api.Tag;

/**
 * @author Dinesh
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Regression
@Tag("smoke")
public @interface Smoke {}
