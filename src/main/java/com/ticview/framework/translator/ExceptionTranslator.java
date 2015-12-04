package com.ticview.framework.translator;

import com.ticview.framework.response.ResponseUtil;
import com.ticview.framework.response.Result;
import com.ticview.framework.exception.TException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by weiduan on 2015/11/23 0023.
 */
@ControllerAdvice
@RestController
@ResponseStatus(HttpStatus.OK)
public class ExceptionTranslator {

    @ExceptionHandler(TException.class)
    public Result processTException(TException te) {
        return te.getResult();
    }

    @ExceptionHandler(Exception.class)
    public Result processException(Exception te) {
        te.printStackTrace();
        return ResponseUtil.getFaultResult();
    }


}
