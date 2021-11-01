package com.zyk.weinxin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.zyk.weinxin"},exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
public class WeinxinApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeinxinApplication.class, args);
    }

}
