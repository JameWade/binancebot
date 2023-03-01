package com.example.demo.common.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.Data;

/**
 * @author cao
 * @Version V1.0.0
 * @ClassName JsonResponse
 * @date 2022/3/22 15:56
 * @Description TODO
 */
@Data
public class JsonResponse<T> {

    /**
     * 此次数据请求结果,默认为false,出错的情况下也为false,如：数据库操作失败、程序出错等
     * 状态码：200成功，其它数值：请查看HttpStatus状态码
     */
    private int code;

    /**
     * 显示给用户看的信息,如：“操作成功!”
     */
    private String msg;

    /**
     * 给开发人员看的信息,正常情况下为空,出错的时候会显示错误的信息
     */
    private String debugMsg;

    /**
     * 结果数据集
     */
    private T data;

    /**
     * 构造函数
     */
    public JsonResponse() { }

    /**
     * 成功构造函数
     * @param code 返回码
     * @param msg  提示信息
     * @param data 返回数据
     */
    private JsonResponse(int code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 失败构造函数
     * @param code 返回码
     * @param msg  提示信息
     * @param ex   Exception
     */
    private JsonResponse(int code, String msg, Exception ex){
        this.code = code;
        this.msg = msg;
        if(ex != null){
            this.debugMsg = ex.getMessage();
        }
        this.data = null;
    }

    /**
     * 操作成功时调用成功函数进行赋值
     * @param data 返回数据
     * @param <T>  任意类型
     * @return JsonResponse<T>
     */
    public static<T> JsonResponse<T> success(T data) {
        return new JsonResponse<>(200, "", data);
    }

    /**
     * 操作成功时调用成功函数进行赋值
     * @param msg  提示信息
     * @param data 返回数据
     * @param <T>  任意类型
     * @return JsonResponse<T>
     */
    public static<T> JsonResponse<T> success(String msg, T data) {
        return new JsonResponse<>(200, msg, data);
    }

    /**
     * 操作失败时调用失败函数进行赋值
     * @param msg 提示信息
     * @param ex  Exception
     * @param <T> 任意类型
     * @return JsonResponse<T>
     */
    public static<T> JsonResponse<T> error(String msg, Exception ex) {
        return new JsonResponse<>(403, msg, ex);
    }

    /**
     * 操作失败时调用失败函数进行赋值
     * @param errorCode 错误码
     * @param msg 提示信息
     * @param ex  Exception
     * @param <T> 任意类型
     * @return JsonResponse<T>
     */
    public static<T> JsonResponse<T> error(int errorCode,String msg, Exception ex) {
        return new JsonResponse<>(errorCode, msg, ex);
    }

}
