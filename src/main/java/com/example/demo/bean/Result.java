package com.example.demo.bean;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class Result {
    private String symbol;//交易对昵称
    List<BigDecimal> listEMA;              ///ema30 近三日结果
    List<BigDecimal>  listPrice;          ///近三日收盘价格

    public boolean compare(){
        boolean flag = true;
        for (int i=0;i<3;i++){
            if (this.listPrice.get(i).compareTo(this.listEMA.get(i)) <0){
                flag = false;
            }
        }
        if (this.listPrice.get(1).compareTo(this.listEMA.get(0))<0 ||
                this.listPrice.get(2).compareTo(this.listEMA.get(1))<0){
            flag = false;
        }
        return flag;
    }
}
