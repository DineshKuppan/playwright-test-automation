package com.bytes2gram.dto;

import com.univocity.parsers.annotations.Parsed;
import lombok.Getter;
import lombok.ToString;

/**
 * @author Dinesh
 */
@Getter
@ToString(callSuper = true)
public class LoginDto extends BaseDto {

  @Parsed(field = "Username", defaultNullRead = "")
  private String username;

  @Parsed(field = "Password", defaultNullRead = "")
  private String password;

  @Parsed(field = "Error Message", defaultNullRead = "")
  private String errorMessage;
}
