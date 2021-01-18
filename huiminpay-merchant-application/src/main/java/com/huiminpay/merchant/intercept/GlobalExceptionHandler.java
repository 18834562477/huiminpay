package com.huiminpay.merchant.intercept;

import com.huiminpay.common.domain.BusinessException;
import com.huiminpay.common.domain.CommonErrorCode;
import com.huiminpay.common.domain.ErrorCode;
import com.huiminpay.common.domain.RestErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * ClassName: GlobalExceptionHandler
 * Description: 全局异常捕获
 * date: 2021/1/18 16:31
 *
 * @author ZhangJie
 * @since JDK 1.8
 */

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  //服务器内部错误
    public RestErrorResponse ProcessException(HttpServletRequest request,
                                              HttpServletResponse response,
                                              Exception exception){
        log.error(exception.getMessage());

        if (exception instanceof BusinessException){
            //响应给前端的异常
            RestErrorResponse restErrorResponse = new RestErrorResponse();
            BusinessException businessException = (BusinessException) exception;
            //获取异常的状态码和信息
            ErrorCode errorCode = businessException.getErrorCode();
            //响应给前端的异常状态码和信息
            restErrorResponse.setErrCode(errorCode.getCode());
            restErrorResponse.setErrMessage(errorCode.getDesc());
            return restErrorResponse;
        }

        return new RestErrorResponse(CommonErrorCode.UNKOWN.getCode(),CommonErrorCode.UNKOWN.getDesc());
    }
}
