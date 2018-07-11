package com.wechart.store.vo;

import lombok.Data;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: ResultDto
 * @包名: com.wechart.store.dto
 * @描述: result结构
 * @日期: 2018/5/20 22:58
 */
@Data
public class ResultVO<T> {
    private Integer code;
    private String message;
    private T data;

    public ResultVO(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultVO() {
    }
}
