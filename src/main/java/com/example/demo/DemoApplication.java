package com.example.demo;

import com.example.demo.config.InitConfig;
import com.example.demo.config.SymbolConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableScheduling
public class DemoApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);

        //初始化交易对
        InitConfig.init();
        //打印已存在的交易对
        System.out.println(SymbolConfig.martket_tickers);
        System.out.println(SymbolConfig.martket_tickers.size());
    }

}
