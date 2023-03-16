package com.fc.common;

import lombok.Data;

import java.io.Serializable;

import static com.fc.define.ResultCodeEnum.*;

/**
 * 返回结果对象
 * @author yfc
 */
@Data
public class FcResult<T> implements Serializable {

    private static final long serialVersionUID = 8094069767564096505L;


    /** 返回状态码 */
    private String code;

    /** 结果信息 */
    private String msg;

    /** 数据 */
    private T data;

    /**
     * 构造函数
     */
    public FcResult() {
        this.code = CODE_SUCCESS.getCode();
    }

    /**
     * 构造函数
     * @param code			状态码
     * @param msg			信息
     * @param data			数据
     */
    public FcResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public FcResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static FcResult success(String msg, Object data){
        return new FcResult(CODE_SUCCESS.getCode(), msg, data);
    }

    public static FcResult success(Object data){
        return new FcResult(CODE_SUCCESS.getCode(), "", data);
    }

    public static FcResult success(){
        return new FcResult(CODE_SUCCESS.getCode(), "", null);
    }

    public static FcResult fail(String msg){
        return new FcResult(CODE_FAIL.getCode(), msg, null);
    }

    public static FcResult error(String msg){
        return new FcResult(CODE_ERROR.getCode(), msg, null);
    }



}
