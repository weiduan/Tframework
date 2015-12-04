package com.ticview.framework.exception;

import com.ticview.framework.response.ResponseUtil;
import com.ticview.framework.response.Result;

/**
 * Created by weiduan on 2015/10/26.
 */
public class TException extends RuntimeException {

    public Result getResult() {
        return ResponseUtil.getFaultResult();
    }
}
