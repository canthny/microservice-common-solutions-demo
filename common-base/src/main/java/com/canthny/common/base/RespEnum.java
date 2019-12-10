package com.canthny.common.base;

/**
 * Description：响应枚举
 * Created By tanghao on 2019/12/10
 */
public enum RespEnum {

    SUCCESS("0000", "成功"),
    FAILURE("7777", "失败"),
    SYS_UNKNOWN_ERROR("9999", "未知系统错误"),
    ;

    private String code;
    private String desc;

    RespEnum(String code, String desc) {
        this.setCode(code);
        this.setDesc(desc);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
