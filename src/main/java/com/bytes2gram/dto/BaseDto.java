package com.bytes2gram.dto;

import com.univocity.parsers.annotations.Parsed;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Dinesh
 */
@Getter
@ToString
public class BaseDto {

  @Parsed(field = "Test Case ID", defaultNullRead = "")
  private String testCaseId;
}
