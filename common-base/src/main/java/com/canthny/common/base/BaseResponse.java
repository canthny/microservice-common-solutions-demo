package com.canthny.common.base;

import java.io.Serializable;

/**
 * Description： 基础响应对象
 * Created By tanghao on 2019/8/26
 */
public class BaseResponse implements Serializable {
    private static final long serialVersionUID = -2284586526021058251L;

    private String code;

    private String msg;

    private String content;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static BaseResponse buildSuccessReponse(){
        BaseResponse response = new BaseResponse();
        response.setCode("0000");
        response.setMsg("成功");
        return response;
    }
}
