package com.ticview.framework.response;

import java.io.Serializable;

/**
 * Created by weiduan on 2015/10/26.
 */
public class Result implements Serializable {

    private String code;

    private String description;

    private Object result;

    public Result(String code, String description) {
        this(code, description, null);
    }

    public Result(String code, String description, Object result) {
        this.code = code;
        this.description = description;
        this.result = result;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "code:" + code + ",description:" + description + ",result:" + result;
    }
}
