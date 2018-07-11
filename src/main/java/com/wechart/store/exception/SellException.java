package com.wechart.store.exception;

import com.wechart.store.enums.ResultStatusEnum;

/**
 * Created by 廖师兄
 * 2017-06-11 18:55
 */
public class SellException extends RuntimeException{

    private Integer code;

    public SellException(ResultStatusEnum resultEnum) {
        super(resultEnum.getMessage());

        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
