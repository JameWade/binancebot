package com.example.demo.common.utils;

import com.alibaba.fastjson.JSONArray;
import com.example.demo.bean.CandleEntry;
import com.example.demo.bean.Result;
import com.example.demo.config.SymbolConfig;
import org.ta4j.core.BarSeries;
import org.ta4j.core.BaseTradingRecord;
import org.ta4j.core.Strategy;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.indicators.EMAIndicator;
import org.ta4j.core.indicators.ParabolicSarIndicator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 指标工具集
 */
public class IndicatorUtils {

    /**
     * 根据数据源计算ema，把后三天的数据进行村粗
     * @param symbol 交易对
     * @param list 数据源
     * @return
     */
    public static final Result getEXPMA(final String symbol, final List<CandleEntry> list) {
        Result result = new Result();
        result.setSymbol(symbol);
        int total = list.size();
        List<BigDecimal> emas = new ArrayList<>();
        List<BigDecimal> prices = new ArrayList<>();
        // 开始计算EMA值，
        BigDecimal k = new BigDecimal("2").divide(new BigDecimal(SymbolConfig.cycle).add(BigDecimal.ONE),4, RoundingMode.DOWN);// 计算出序数
        BigDecimal ema = list.get(0).getEndPrice();// 第一天ema等于当天收盘价
        for (int i = 1; i < list.size(); i++) {
            BigDecimal endPrice = list.get(i).getEndPrice();
            // 第二天以后，当天收盘 收盘价乘以系数再加上昨天EMA乘以系数-1
            ema = endPrice.multiply(k).add(ema.multiply((BigDecimal.ONE.subtract(k))));
            //保存后四天的值
            if (total - i <= 4) {
                emas.add(ema.setScale(8,RoundingMode.DOWN));
                prices.add(endPrice.setScale(8,RoundingMode.DOWN));
            }
        }
        result.setListEMA(emas);
        result.setListPrice(prices);
        return result;
    }

    public static void main1(String[] args) {
        // 开始计算EMA值，
        BigDecimal k = new BigDecimal("2").divide(new BigDecimal(SymbolConfig.cycle).add(BigDecimal.ONE),4, RoundingMode.DOWN);// 计算出序数
        System.out.println(k);
        //0.0645
        List<BigDecimal> list = Arrays.asList(new BigDecimal("100"), new BigDecimal("200"), new BigDecimal("300"), new BigDecimal("400"), new BigDecimal("500"), new BigDecimal("600"));
        BigDecimal ema = list.get(0);// 第一天ema等于当天收盘价
        for (int i = 1; i < list.size(); i++) {
            // 第二天以后，当天收盘 收盘价乘以系数再加上昨天EMA乘以系数-1
            ema = list.get(i).multiply(k).add(ema.multiply((BigDecimal.ONE.subtract(k))));
            System.out.println(ema);
            /**
             * 0.0645
             * 106.4500
             * 118.93397500
             * 137.062733612500
             * 160.4721872944937500
             * 188.82173121399890312500
             */
        }


    }

    public static void main2(String[] args) {
        // 开始计算EMA值，
        List<Double> list = Arrays.asList(100.0,200.0,300.0,400.0,500.0,600.0);

        Double k = 2.0 / (30 + 1.0);// 计算出序数
        System.out.println(k);
        Double ema = list.get(0);// 第一天ema等于当天收盘价
        for (int i = 1; i < list.size(); i++) {
            // 第二天以后，当天收盘 收盘价乘以系数再加上昨天EMA乘以系数-1
            ema = list.get(i) * k + ema * (1 - k);
            System.out.println(ema);
            /**
             * 0.06451612903225806
             * 106.45161290322582
             * 118.93860561914674
             * 137.07159880500825
             * 160.48633436597547
             * 188.84205472946093
             */
        }

    }

    public static void main3(String[] args) {
        String str  ="[\n" +
                "    [\n" +
                "        1676851200000,\n" +
                "        \"311.60000000\",\n" +
                "        \"319.70000000\",\n" +
                "        \"308.20000000\",\n" +
                "        \"315.50000000\",\n" +
                "        \"249553.67200000\",\n" +
                "        1676937599999,\n" +
                "        \"78818640.22960000\",\n" +
                "        141370,\n" +
                "        \"130993.68400000\",\n" +
                "        \"41387906.71800000\",\n" +
                "        \"0\"\n" +
                "    ],\n" +
                "    [\n" +
                "        1676937600000,\n" +
                "        \"315.50000000\",\n" +
                "        \"317.40000000\",\n" +
                "        \"309.50000000\",\n" +
                "        \"311.60000000\",\n" +
                "        \"210692.64300000\",\n" +
                "        1677023999999,\n" +
                "        \"66082807.82920000\",\n" +
                "        121979,\n" +
                "        \"100204.60400000\",\n" +
                "        \"31436689.33760000\",\n" +
                "        \"0\"\n" +
                "    ],\n" +
                "    [\n" +
                "        1677024000000,\n" +
                "        \"311.60000000\",\n" +
                "        \"312.80000000\",\n" +
                "        \"303.40000000\",\n" +
                "        \"312.40000000\",\n" +
                "        \"212384.96400000\",\n" +
                "        1677110399999,\n" +
                "        \"65267214.42810000\",\n" +
                "        131339,\n" +
                "        \"105171.89000000\",\n" +
                "        \"32324677.31740000\",\n" +
                "        \"0\"\n" +
                "    ],\n" +
                "    [\n" +
                "        1677110400000,\n" +
                "        \"312.40000000\",\n" +
                "        \"315.20000000\",\n" +
                "        \"305.90000000\",\n" +
                "        \"308.40000000\",\n" +
                "        \"162827.39100000\",\n" +
                "        1677196799999,\n" +
                "        \"50507417.85890000\",\n" +
                "        102213,\n" +
                "        \"86205.36000000\",\n" +
                "        \"26756122.00810000\",\n" +
                "        \"0\"\n" +
                "    ],\n" +
                "    [\n" +
                "        1677196800000,\n" +
                "        \"308.40000000\",\n" +
                "        \"311.90000000\",\n" +
                "        \"298.60000000\",\n" +
                "        \"302.30000000\",\n" +
                "        \"170596.24600000\",\n" +
                "        1677283199999,\n" +
                "        \"52084433.42630000\",\n" +
                "        113096,\n" +
                "        \"83046.97700000\",\n" +
                "        \"25357372.97130000\",\n" +
                "        \"0\"\n" +
                "    ],\n" +
                "    [\n" +
                "        1677283200000,\n" +
                "        \"302.30000000\",\n" +
                "        \"303.00000000\",\n" +
                "        \"295.60000000\",\n" +
                "        \"302.40000000\",\n" +
                "        \"101836.09500000\",\n" +
                "        1677369599999,\n" +
                "        \"30591721.01810000\",\n" +
                "        79357,\n" +
                "        \"51075.70200000\",\n" +
                "        \"15349582.25630000\",\n" +
                "        \"0\"\n" +
                "    ],\n" +
                "    [\n" +
                "        1677369600000,\n" +
                "        \"302.30000000\",\n" +
                "        \"309.70000000\",\n" +
                "        \"300.80000000\",\n" +
                "        \"308.70000000\",\n" +
                "        \"94593.37500000\",\n" +
                "        1677455999999,\n" +
                "        \"28878641.70620000\",\n" +
                "        63239,\n" +
                "        \"53359.59300000\",\n" +
                "        \"16289416.19600000\",\n" +
                "        \"0\"\n" +
                "    ],\n" +
                "    [\n" +
                "        1677456000000,\n" +
                "        \"308.60000000\",\n" +
                "        \"309.40000000\",\n" +
                "        \"300.50000000\",\n" +
                "        \"304.70000000\",\n" +
                "        \"136920.49000000\",\n" +
                "        1677542399999,\n" +
                "        \"41752882.74370000\",\n" +
                "        85146,\n" +
                "        \"67950.87000000\",\n" +
                "        \"20720935.95980000\",\n" +
                "        \"0\"\n" +
                "    ],\n" +
                "    [\n" +
                "        1677542400000,\n" +
                "        \"304.60000000\",\n" +
                "        \"305.70000000\",\n" +
                "        \"300.10000000\",\n" +
                "        \"301.40000000\",\n" +
                "        \"90335.14300000\",\n" +
                "        1677628799999,\n" +
                "        \"27369852.27950000\",\n" +
                "        68431,\n" +
                "        \"43553.04700000\",\n" +
                "        \"13203204.65990000\",\n" +
                "        \"0\"\n" +
                "    ],\n" +
                "    [\n" +
                "        1677628800000,\n" +
                "        \"301.50000000\",\n" +
                "        \"306.30000000\",\n" +
                "        \"300.60000000\",\n" +
                "        \"302.50000000\",\n" +
                "        \"89595.88400000\",\n" +
                "        1677715199999,\n" +
                "        \"27199585.30730000\",\n" +
                "        57094,\n" +
                "        \"46160.48000000\",\n" +
                "        \"14019435.71220000\",\n" +
                "        \"0\"\n" +
                "    ]\n" +
                "]";
        List<Double> list = new ArrayList<>();
        JSONArray parse = (JSONArray) JSONArray.parse(str);
        for(Object obj :parse){
            JSONArray data = (JSONArray) JSONArray.parse(obj.toString());
            list.add(data.getDouble(4));
        }

        System.out.println(list);
        Double k = 2.0 / (30 + 1.0);// 计算出序数
        System.out.println(k);
        Double ema = list.get(0);// 第一天ema等于当天收盘价
        for (int i = 1; i < list.size(); i++) {
            // 第二天以后，当天收盘 收盘价乘以系数再加上昨天EMA乘以系数-1
            ema = list.get(i) * k + ema * (1 - k);
            System.out.println(ema);
        }
    }

/*    public static void main4(String[] args) {
        BinanceSpotApiClientFactory factory = BinanceSpotApiClientFactory.newInstance("",
                "",
                "https://api3.binance.com",
                "wss://stream.binance.com:9443"

        );
        BinanceApiSpotRestClient client = factory.newRestClient();
        List<Candlestick> candlesticks = client.getCandlestickBars("BNBBUSD", CandlestickInterval.DAILY);
        System.out.println(candlesticks);
        int size = candlesticks.size();
        int index = 7;
        if(size > 30){
            if(size > 200){
                index = 90;
            }
        }else{
           return;
        }
        BarSeries barSeries = BinanceTa4jUtil.convertToBarSeries(candlesticks.subList(0, candlesticks.size() - index), "BNBBUSD", CandlestickInterval.DAILY.getIntervalId());
        Strategy strategy = BinanceTa4jUtil.buildStrategy(barSeries, "EMA");
        TradingRecord tradingRecord = new BaseTradingRecord();
        //ParabolicSarIndicator sarIndicator = new ParabolicSarIndicator(series);
        //EMAIndicator avg14 = new EMAIndicator(closePrice, 14);

    }*/

    public static void main(String[] args) {


    }
}
