package com.yuan.learnproject.exception;

import com.yuan.learnproject.base.BaseException;

/**
 * @author yuan
 * @date 2019/3/2
 **/
public class ApiException extends BaseException {

    public ApiException(int errorCode, String errorMsg) {
        super(errorCode, errorMsg);
    }
}
