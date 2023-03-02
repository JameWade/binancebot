package com.example.demo.timedTask;

import com.example.demo.bean.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class ComeputeService {

    public List<HashMap<String, Object>> compute_ema30(List<Result> list) {
       String label = "今日的EMA(30)价格在：";
        List<HashMap<String, Object>> symbolList = new ArrayList<>(); //map is symbol:ema30
        for (Result result : list) {
            if (result.compare()) {
                HashMap<String, Object> map = new HashMap<>();
                map.put(result.getSymbol(), label + result.getListEMA().get(2));
                symbolList.add(map);
            }
        }
        return symbolList;
    }

}
