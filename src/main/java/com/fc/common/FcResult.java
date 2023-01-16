package com.fc.common;

import java.io.Serializable;

import static com.fc.define.ResultCodeEnum.*;

/**
 * 返回结果对象
 * @author yangfucheng
 */
public class FcResult<T> implements Serializable {

    private static final long serialVersionUID = 8094069767564096505L;



    /** 返回状态码 */
    private String code;

    /** 结果信息 */
    private String msg;

    /** 数据 */
    private Object data;

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
    public FcResult(String code, String msg, Object data) {
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

    public static FcResult fail(String msg){
        return new FcResult(CODE_FAIL.getCode(), msg, null);
    }

    public static FcResult error(String msg){
        return new FcResult(CODE_ERROR.getCode(), msg, null);
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * @return the data
     * @return
     */
    public <T> T getData() {
        return (T) data;
    }

    /**
     * @param data the data to set
     */
    public void setData(Object data) {
        this.data = data;
    }

}
