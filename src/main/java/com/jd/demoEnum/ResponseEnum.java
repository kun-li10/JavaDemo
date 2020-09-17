package com.jd.demoEnum;

/**
 * @Author lk
 * @Date 2020/3/1 21:39
 * @Version 1.0
 */
public enum ResponseEnum {
    /**
     * code:标识
     * mes:描述
     */
    ERROR_MSG(0, "系统内存泄漏!"),
    HTTP_FIIL(1, "请求失败!"),
    ERROR_EXCEPITON(2, "核心失败!");
    Integer code;
    String msg;

    ResponseEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public static void main(String[] args) {
        for (int i = 0; i < ResponseEnum.values().length; i++) {
            System.out.println("枚举成员变量: " + ResponseEnum.values()[i]);
            System.out.println(ResponseEnum.valueOf("ERROR_EXCEPITON"));
        }
    }
}
