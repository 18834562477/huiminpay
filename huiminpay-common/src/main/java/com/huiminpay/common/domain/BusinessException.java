package com.huiminpay.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: BusinessException
 * Description: 业务异常类
 * date: 2021/1/18 16:25
 *
 * @author ZhangJie
 * @since JDK 1.8
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException{
    private ErrorCode errorCode;
}
