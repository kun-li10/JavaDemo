package com.jd.demoEnum;

/**
 * 自定义异常枚举
 *
 * @Author lk
 * @Date 2020/3/1 21:47
 * @Version 1.0
 */
public enum RuntimeExceptionEnum {
    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消");
    Integer code;
    String msg;

    RuntimeExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }}
