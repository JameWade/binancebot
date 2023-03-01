package com.example.demo.common.utils;

import com.example.demo.bean.CandleEntry;
import com.example.demo.bean.Result;
import com.example.demo.config.SymbolConfig;

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
            total--;
            BigDecimal endPrice = list.get(i).getEndPrice();
            // 第二天以后，当天收盘 收盘价乘以系数再加上昨天EMA乘以系数-1
            ema = endPrice.multiply(k).add(ema).multiply((BigDecimal.ONE.subtract(k)));
            //保存后三天的值
            if (total - i <= 3) {
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
            ema = list.get(i).multiply(k).add(ema).multiply((BigDecimal.ONE.subtract(k)));
            System.out.println(ema);
            /**
             * 105.61795000
             * 116.907517225000
             * 133.5028823639875000
             * 155.06182145151030625000
             * 181.264183967887891496875000
             */
        }


    }

    public static void main(String[] args) {
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
}
