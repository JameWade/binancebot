package com.example.demo.config;


import com.example.demo.common.okhttp.OkHttpUtils;

/**
 * 初始化交易对
 */
public class InitConfig {

    /**
     * 初始化 交易对数据
     * @throws Exception
     */
    public static void init() throws Exception {
        //获取请求地址
        String priceUrl = SymbolConfig.baseUrl + SymbolConfig.pricePath;
         OkHttpUtils.builder()
                .url(priceUrl)
                .get()
                .sync();
    }

    public static void main(String[] args) {
        String async = OkHttpUtils.builder()
                .url("https://api.binance.com/api/v3/ticker/price")
                .get()
                .sync();
        System.out.println(async);
    }
}
