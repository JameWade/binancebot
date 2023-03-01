package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.example.demo.common.utils.SpringUtils;
import com.example.demo.config.InitConfig;
import com.example.demo.config.SymbolConfig;
import com.example.demo.timedTask.TimedSerivce;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
@EnableScheduling
@Slf4j
public class DemoApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(DemoApplication.class, args);

        //初始化交易对
        InitConfig.init();
        //打印已存在的交易对
        log.info(SymbolConfig.martket_tickers + "");
        log.info(SymbolConfig.martket_tickers.size() + "");

        //初始化交易对的1000条数据
        TimedSerivce timedSerivce = new TimedSerivce();
        timedSerivce.get_historical_data();
        log.info("初始化 交易对的1000条数据 已完成");
        //初始化计算数据
        timedSerivce.data_input();
        log.info("初始化 展示数据 已完成");
        //System.out.println(JSONArray.parseArray(JSON.toJSONString(SymbolConfig.candleEntryLists)));

    }

}
