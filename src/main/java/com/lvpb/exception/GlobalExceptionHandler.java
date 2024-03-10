package com.lvpb.exception;


import com.lvpb.common.CommonCode;
import com.lvpb.common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 系统异常处理器
     * @param exception 系统异常
     * @return 统一返回结果
     */
    @ExceptionHandler(Exception.class)
    public Result<Void> handlerException(Exception exception){
        log.error("Exception:{}",exception.getMessage(),exception);
        return Result.fail(CommonCode.SERVER_ERROR);
    }

    /**
     * 自定义异常处理器
     * @param exception 自定义异常
     * @return 统一返回结果
     */
    @ExceptionHandler(BusinessException.class)
    public Result<Void> handlerException(BusinessException exception){
        log.error("BusinessException:{}",exception.getMessage(),exception);
        return Result.fail(exception.getCode(),exception.getMessage(),exception.getDesc());
    }

    /**
     * 参数缺失异常处理器
     * @param exception 参数缺失异常
     * @return 统一返回结果
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result<Void> handlerMethodArgumentNotValidException(HttpMessageNotReadableException exception){
        log.error("HttpMessageNotReadableException:{}",exception.getMessage(),exception);
        return Result.fail(ExceptionCode.MISS_REQUEST_PARAMS);
    }

    /**
     * 参数校验失败异常处理器
     * @param exception 参数校验失败异常
     * @return 统一返回结果
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result<Void> handlerMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        log.error("MethodArgumentNotValidException:{}",exception.getMessage(),exception);
        return Result.fail(ExceptionCode.REQUEST_PARAMS_IS_EMPTY);
    }
}
