package com.example.demo.config;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.demo.common.okhttp.OkHttpUtils;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


/**
 * 初始化交易对
 */
@Slf4j
public class InitConfig {

    /**
     * 初始化 交易对数据
     *
     * @throws Exception
     */
    public static void init() throws Exception {
        List<String> martket_tickers = new ArrayList<>();
        //获取请求地址
        String priceUrl = SymbolConfig.baseUrl + SymbolConfig.pricePath;
        String pricesStr = OkHttpUtils.builder()
                .url(priceUrl)
                .get()
                .sync();
        JSONArray parse = (JSONArray) JSONArray.parse(pricesStr);
        parse.forEach(x -> {
            JSONObject data = (JSONObject) x;
            //不等于 -1 说明匹配到了关键字 进行存储
            String symbol = data.getString("symbol");
            if (symbol.substring(symbol.length() - 4).indexOf("BUSD") != -1) {
                martket_tickers.add(symbol);
            }
        });

        if (martket_tickers == null || martket_tickers.isEmpty()) {
            log.error("初始化交易对失败");
            throw new Exception("初始化交易对失败");
        }
        SymbolConfig.martket_tickers = martket_tickers;
    }

    public static void main(String[] args) {
        List<String> martket_tickers = new ArrayList<>();
        String async = OkHttpUtils.builder()
                .url("https://api.binance.com/api/v3/ticker/price")
                .get()
                .sync();

        JSONArray parse = (JSONArray) JSONArray.parse(async);
        for (Object obj : parse) {
            JSONObject data = (JSONObject) obj;

            //不等于 -1 说明匹配到了关键字 进行存储
            String symbol = data.getString("symbol");
            //
            if (symbol.substring(symbol.length() - 4).indexOf("BUSD") != -1) {
                martket_tickers.add(symbol);
            }
        }
        System.out.println(martket_tickers);
    }
}
