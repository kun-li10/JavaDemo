package com.jd.demoEnum;

/**
 * 自定义异常类
 *
 * @Author lk
 * @Date 2020/3/1 21:53
 * @Version 1.0
 */
public class NewRuntimeException extends RuntimeException {

    private Integer code;

    public NewRuntimeException(RuntimeExceptionEnum result) {
        super(result.getMsg());
        this.code = result.getCode();
    }

    public NewRuntimeException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
