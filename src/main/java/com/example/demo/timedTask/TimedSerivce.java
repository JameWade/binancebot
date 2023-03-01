package com.example.demo.timedTask;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.bean.CandleEntry;
import com.example.demo.bean.CandleEntryList;
import com.example.demo.bean.Result;
import com.example.demo.common.okhttp.OkHttpUtils;
import com.example.demo.common.utils.IndicatorUtils;
import com.example.demo.common.utils.SpringUtils;
import com.example.demo.config.InitConfig;
import com.example.demo.config.SymbolConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
@Slf4j
public class TimedSerivce {

    /**
     * 两个小时更新一次
     * @throws Exception
     */
    @Scheduled(cron = "0 0 12 * * ?")
    public void update_market_code() throws Exception {
        try {
            InitConfig.init();
            log.info("{},更新交易对，交易对总数量为：{}",LocalDateTime.now(), SymbolConfig.martket_tickers.size());
        }catch (Exception e){
            log.error("{},更新交易对失败。错误信息为：{}", LocalDateTime.now(),e.getMessage());
        }
    }

    /**
     * 定时更新 交易对的1000条收盘数据
     */
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void get_historical_data(){
        String klinesUrl = SymbolConfig.baseUrl + SymbolConfig.klinesPath;

        List<String> martket_tickers =  SymbolConfig.martket_tickers;
        List<CandleEntryList> candleData = new ArrayList<>();

        for(int i = 0 ; i < martket_tickers.size() ; i++){
            String symbolName = martket_tickers.get(i);
            List<CandleEntry> candles = new ArrayList<>();
            //根据交易对 获取1000条数据
            String dataStr = OkHttpUtils.builder()
                    .url(klinesUrl)
                    .addParam("symbol",symbolName)
                    .addParam("interval","1d")
                    .addParam("limit","1000")
                    .get()
                    .sync();
            JSONArray objs = (JSONArray) JSONArray.parse(dataStr);
            //处理数据源
            for(int o = 0 ; o < objs.size(); o++){
                JSONArray parse = (JSONArray) objs.get(o);
                CandleEntry buildCandle = CandleEntry.builder()
                        .startTime(parse.getString(0))
                        .openingPrice(parse.getBigDecimal(1))
                        .highestPrice(parse.getBigDecimal(2))
                        .lowestPrice(parse.getBigDecimal(3))
                        .endPrice(parse.getBigDecimal(4))
                        .transactionVolume(parse.getBigDecimal(5))
                        .endTime(parse.getString(6))
                        .turnover(parse.getBigDecimal(7))
                        .transactionNumber(parse.getInteger(8))
                        .tradingVolume(parse.getBigDecimal(9))
                        .tradingTurnover(parse.getBigDecimal(10))
                        .other(parse.getBigDecimal(11))
                        .build();
                candles.add(buildCandle);
            }
            candleData.add(CandleEntryList.builder().symbol(symbolName).candleEntries(candles).build());
        }
        //更新交易对最新1000条收盘数据
        SymbolConfig.candleEntryLists = candleData;
    }

    /**
     * 定时 填写响应数据 进过ema处理
     */
    @Scheduled(cron = "0 0/30 * * * ? ")
    public void data_input(){
        List<Result> resultList = new ArrayList<>();
        List<CandleEntryList> list =  SymbolConfig.candleEntryLists;
        for(int i = 0 ; i < list.size() ; i++){
            CandleEntryList candleEntryList = list.get(i);
            String symbol = candleEntryList.getSymbol();
            List<CandleEntry> candleEntries = candleEntryList.getCandleEntries();
            //数据源小于5 不作处理
            if(candleEntries.size() < 5){
                continue;
            }
            resultList.add(IndicatorUtils.getEXPMA(symbol,candleEntries));
        }
        ComeputeService comeputeService = SpringUtils.getBean(ComeputeService.class);
        List<HashMap<String, BigDecimal>> hashMaps = comeputeService.compute_ema30(resultList);
        SymbolConfig.full_data = hashMaps;
    }

}
