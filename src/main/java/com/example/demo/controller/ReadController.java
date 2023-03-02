package com.example.demo.controller;

import com.example.demo.common.utils.JsonResponse;
import com.example.demo.config.SymbolConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cao
 * @Version V1.0.0
 * @ClassName CalculationController
 * @date 2022/3/31 18:56
 * @Description 交易开关类
 */
@RestController
@RequestMapping(value = "/v1", produces = {"application/json;charset=UTF-8"})
public class ReadController {

    /**
     * <p>
     * 关闭交易  127.0.0.1:8993/v1/strong_coin
     */
    @GetMapping("cryptos")
    public JsonResponse<Object> dj() {
        if(SymbolConfig.full_data == null || SymbolConfig.full_data.isEmpty()){
            return JsonResponse.success("获取数据成功", "啊哦，没有符合的交易数据，建议去彬哥的交易所！");
        }else {
            return JsonResponse.success("获取数据成功", SymbolConfig.full_data);
        }
    }

    /**
     * <p>
     * 关闭交易  127.0.0.1:8993/v1/busd_martket
     */
    @GetMapping("busd_martket")
    public JsonResponse<Object> busd_martket() {
        return JsonResponse.success("获取数据成功", SymbolConfig.martket_tickers);
    }
}
