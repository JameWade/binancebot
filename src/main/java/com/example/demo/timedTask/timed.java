package com.example.demo.timedTask;

import com.example.demo.config.InitConfig;
import com.example.demo.config.SymbolConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class timed {


    /**
     * 两个小时更新一次
     * @throws Exception
     */
    @Scheduled(cron = "0 15 0/2 * * ?")
    public void update_market_code() throws Exception {
        try {
            InitConfig.init();
            log.info("{},更新交易对，交易对总数量为：{}",LocalDateTime.now(), SymbolConfig.martket_tickers.size());
        }catch (Exception e){
            log.error("{},更新交易对失败。错误信息为：{}", LocalDateTime.now(),e.getMessage());
        }
    }
}
