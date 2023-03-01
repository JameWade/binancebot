package com.example.demo.bean;

import java.math.BigDecimal;

public class CandleEntry {
    String startTime;                                // k线开盘时间
    BigDecimal openingPrice;                              // 开盘价
    BigDecimal highestPrice;                              // 最高价
    BigDecimal lowestPrice;                               // 最低价
    BigDecimal endPrice;                                 // 收盘价(当前K线未结束的即为最新价)
    BigDecimal transactionVolume;                     // 成交量
    String endTime;                                            // k线收盘时间
    BigDecimal turnover;                                              // 成交额
    int transactionNumber;                                         // 成交笔数
    BigDecimal tradingVolume;                                    // 主动买入成交量
    BigDecimal tradingTurnover;                              // 主动买入成交额
    BigDecimal other;                                      // 请忽略该参数
}
