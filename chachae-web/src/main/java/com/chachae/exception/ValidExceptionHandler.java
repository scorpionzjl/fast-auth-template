package com.chachae.exception;

import com.chachae.common.Result;
import com.google.common.collect.Maps;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;

/**
 * @author chachae
 * @date 2019/11/13 13:28
 */
@RestControllerAdvice
public class ValidExceptionHandler {

  /**
   * 前端数据校验异常
   *
   * @param ex 异常
   * @return 校验异常信息
   */
  @ExceptionHandler(BindException.class)
  public Result handleValidationExceptions(BindException ex) {
    Map<String, String> errors = Maps.newHashMap();
    List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
    for (ObjectError error : allErrors) {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    }
    return Result.fail("数据校验不通过", errors);
  }
}
