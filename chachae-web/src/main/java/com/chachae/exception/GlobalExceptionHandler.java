package com.chachae.exception;

import com.chachae.common.core.bean.Result;
import com.chachae.common.core.enums.REnum;
import com.chachae.common.core.exception.ApiException;
import com.chachae.common.core.utils.DateUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * @author chachae
 * @date 2019/11/14 16:01
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

  private final Map<String, Object> apiErrMap = Maps.newHashMap();

  /**
   * 业务层异常
   *
   * @param ex 异常
   * @return 异常响应信息
   */
  @ExceptionHandler(ApiException.class)
  public Result serviceExceptionHandler(ApiException ex, HttpServletRequest request) {
    Map<String, Object> apiErrMap = exceptionMsgTemplate(ex, request);
    return Result.fail(ex.getCode(), REnum.FAIL.desc, apiErrMap);
  }

  /**
   * 权限不足异常
   *
   * @param ex 异常
   * @param request 请求信息
   * @return 异常响应信息
   */
  @ExceptionHandler(UnauthorizedException.class)
  public Result unauthorizedExceptionHandler(UnauthorizedException ex, HttpServletRequest request) {
    Map<String, Object> apiErrMap = exceptionMsgTemplate(ex, request);
    return Result.fail(REnum.UN_AUTHORIZED.val, REnum.UN_AUTHORIZED.desc, apiErrMap);
  }

  /**
   * 前端表单请求数据异常
   *
   * @param ex 异常
   * @return 异常响应信息
   */
  @ExceptionHandler(BindException.class)
  public Result handleValidationExceptions(BindException ex) {
    List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
    Map<String, String> errors = validExceptionTemplate(allErrors);
    return Result.fail(REnum.ARG_ERROR.val, REnum.ARG_ERROR.desc, errors);
  }

  /**
   * 前端Json 请求数据异常
   *
   * @param ex 异常
   * @return 异常响应信息
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public Result handleValidationExceptions(MethodArgumentNotValidException ex) {
    List<ObjectError> allErrors = ex.getBindingResult().getAllErrors();
    Map<String, String> errors = validExceptionTemplate(allErrors);
    return Result.fail(REnum.ARG_ERROR.desc, errors);
  }

  /**
   * 未知异常【直接监听异常的祖宗：Exception.class 即可】
   *
   * @param ex 异常
   * @return 异常响应信息
   */
  @ExceptionHandler(Exception.class)
  public Result unknownExceptionHandler(Exception ex, HttpServletRequest request) {
    Map<String, Object> apiErrMap = exceptionMsgTemplate(ex, request);
    return Result.fail(REnum.SYSTEM_ERROR.val, REnum.SYSTEM_ERROR.desc, apiErrMap);
  }

  private Map<String, String> validExceptionTemplate(List<ObjectError> allErrors) {
    Map<String, String> errors = Maps.newHashMap();
    for (ObjectError error : allErrors) {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    }
    return errors;
  }

  private Map<String, Object> exceptionMsgTemplate(Exception ex, HttpServletRequest request) {
    String message = ex.getMessage();
    if (ex instanceof ApiException) {
      message = ((ApiException) ex).getMsg();
    }
    String time = DateUtil.nowStr();
    String url = request.getRequestURI();
    log.error("异常发生时间：{}", time);
    log.error("异常接口：{}", url);
    log.error("异常消息：{}", message);
    apiErrMap.put("url", url);
    apiErrMap.put("message", message);
    return apiErrMap;
  }
}
