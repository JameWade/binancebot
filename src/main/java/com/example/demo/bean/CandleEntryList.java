package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class CandleEntryList {
    private String symbol;//交易对昵称
    private List<CandleEntry> candleEntries;
}
