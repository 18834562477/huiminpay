package com.huiminpay.merchant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * ClassName: MerchantBootstrap
 * Description: 启动类
 * date: 2021/1/14 17:32
 *
 * @author ZhangJie
 * @since JDK 1.8
 */
@SpringBootApplication
@MapperScan("com.huiminpay.merchant.mapper")
public class MerchantBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(MerchantBootstrap.class);
    }
}
