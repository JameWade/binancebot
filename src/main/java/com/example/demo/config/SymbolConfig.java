package com.example.demo.config;

import com.example.demo.bean.CandleEntry;
import com.example.demo.bean.CandleEntryList;

import java.util.List;

public class SymbolConfig {


    //存放币安所有市场代码
    public static List<String> martket_tickers;

    //存放所有交易对的最近1000条收盘价格
    public static List<CandleEntryList> candleEntryLists;

    //币安请求路由
    public static String baseUrl = "https://api3.binance.com/api/v3";

    //获取BN所有交易对的api路径
    public static String pricePath = "/ticker/price";

    //获取BN某交易对近1000条收盘数据
    public static String klinesPath = "/klines";


}
