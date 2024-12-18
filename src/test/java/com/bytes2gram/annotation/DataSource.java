package com.bytes2gram.annotation;

import com.bytes2gram.dto.BaseDto;
import com.bytes2gram.util.DataArgumentsProvider;
import java.lang.annotation.*;
import org.junit.jupiter.params.provider.ArgumentsSource;

/**
 * @author Dinesh
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ArgumentsSource(DataArgumentsProvider.class)
public @interface DataSource {

  String id();

  String fileName();

  Class<? extends BaseDto> clazz();
}
