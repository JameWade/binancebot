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
            ema = endPrice.multiply(k).add(ema).multiply(BigDecimal.ONE.subtract(k));
            //保存后三天的值
            if (total - i <= 3) {
                emas.add(ema);
                prices.add(endPrice);
            }
        }
        result.setListEMA(emas);
        result.setListPrice(prices);
        return result;
    }

    public static void main(String[] args) {
        BigDecimal k = new BigDecimal("2").divide(new BigDecimal(SymbolConfig.cycle).add(BigDecimal.ONE),4, RoundingMode.DOWN);// 计算出序数
        System.out.println(k);
    }
}
