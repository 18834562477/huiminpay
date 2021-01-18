package com.huiminpay.common.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ClassName: RestErrorResponse
 * Description:
 * date: 2021/1/18 16:18
 *
 * @author ZhangJie
 * @since JDK 1.8
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestErrorResponse {
        private int errCode;
        private String errMessage;
}
