package com.example.demo.bean;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class CandleEntry {
    private String startTime;                                // k线开盘时间
    private BigDecimal openingPrice;                              // 开盘价
    private BigDecimal highestPrice;                              // 最高价
    private BigDecimal lowestPrice;                               // 最低价
    private BigDecimal endPrice;                                 // 收盘价(当前K线未结束的即为最新价)
    private BigDecimal transactionVolume;                     // 成交量
    private String endTime;                                            // k线收盘时间
    private BigDecimal turnover;                                              // 成交额
    private int transactionNumber;                                         // 成交笔数
    private BigDecimal tradingVolume;                                    // 主动买入成交量
    private BigDecimal tradingTurnover;                              // 主动买入成交额
    private BigDecimal other;                                      // 请忽略该参数

}
