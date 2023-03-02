package com.example.demo.common.utils;

import org.ta4j.core.BarSeries;
import org.ta4j.core.Indicator;
import org.ta4j.core.TradingRecord;
import org.ta4j.core.num.Num;
import org.ta4j.core.rules.AbstractRule;

/**
 * 类说明
 *
 * @author huyong5
 * @version SarRule.java, 2021/8/11 15:21
 */
public class SarRule extends AbstractRule {
    private final Indicator<Num> indicator;
    private       boolean        entry;

    public SarRule(Indicator<Num> indicator, boolean entry) {
        this.indicator = indicator;
        this.entry = entry;
    }

    @Override
    public boolean isSatisfied(int index, TradingRecord tradingRecord) {
        BarSeries barSeries = indicator.getBarSeries();
        int       size      = barSeries.getBarCount();
        if (index == 0 || index >= size) {
            return false;
        }
        if(entry) {
            return isUp(index - 1, barSeries) && !isUp(index, barSeries);
        }else{
            return !isUp(index - 1, barSeries) && isUp(index, barSeries);
        }
    }

    private boolean isUp(int index, BarSeries barSeries) {
        Num closePrice = barSeries.getBar(index).getClosePrice();
        Num sarValue   = indicator.getValue(index);
        return sarValue.isGreaterThan(closePrice);
    }
}