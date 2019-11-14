package com.chachae.exception;

import com.chachae.common.Result;
import com.chachae.utils.DateUtil;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
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
  @ExceptionHandler(ServiceException.class)
  public Result serviceExceptionHandler(ServiceException ex, HttpServletRequest request) {
    Map<String, Object> apiErrMap = exceptionMsgTemplate(ex, request);
    return Result.fail("业务层异常", apiErrMap);
  }

  /**
   * 对象复制异常
   *
   * @param ex 异常
   * @return 异常响应信息
   */
  @ExceptionHandler(PermissionException.class)
  public Result serviceExceptionHandler(PermissionException ex, HttpServletRequest request) {
    Map<String, Object> apiErrMap = exceptionMsgTemplate(ex, request);
    return Result.fail("工具类执行异常", apiErrMap);
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
    return Result.fail("表单请求数据校验不通过", errors);
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
    return Result.fail("Json 请求数据校验不通过", errors);
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
    return Result.fail("未知异常", apiErrMap);
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
    String time = DateUtil.nowStr();
    String url = request.getRequestURI();
    log.error("异常发生时间：{}", time);
    log.error("异常接口：{}", url);
    log.error("异常消息：{}", message);
    apiErrMap.put("time", time);
    apiErrMap.put("url", url);
    apiErrMap.put("message", message);
    return apiErrMap;
  }
}
