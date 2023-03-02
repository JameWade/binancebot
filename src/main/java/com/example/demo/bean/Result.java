package com.example.demo.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
public class Result {
    private String symbol;//交易对昵称
    List<BigDecimal> listEMA;              ///ema30 近三日结果
    List<BigDecimal> listPrice;          ///近三日收盘价格

    public boolean compare() {
        boolean flag = true;
        for (int i = 1; i < 4; i++) {
            if (this.listPrice.get(i).compareTo(this.listEMA.get(i)) < 0) {
                flag = false;
            }
        }
        if (this.listPrice.get(1).compareTo(this.listPrice.get(0)) < 0 ||
                this.listPrice.get(2).compareTo(this.listPrice.get(1)) < 0 ||
                    this.listPrice.get(3).compareTo(this.listPrice.get(2)) < 0) {
            flag = false;
        }
        return flag;
    }
}
