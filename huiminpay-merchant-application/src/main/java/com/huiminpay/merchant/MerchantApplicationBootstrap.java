package com.huiminpay.merchant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * ClassName: MerchantApplicationBootstrap
 * Description: 启动类
 * date: 2021/1/14 17:01
 *
 * @author ZhangJie
 * @since JDK 1.8
 */

@SpringBootApplication
@EnableSwagger2
public class MerchantApplicationBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(MerchantApplicationBootstrap.class,args);
    }
}
