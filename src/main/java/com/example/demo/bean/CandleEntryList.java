package com.example.demo.bean;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class CandleEntryList {
    private String symbol;//交易对昵称
    private List<CandleEntry> candleEntries;
}
