package com.wechart.store.constants;

/**
 * redis常量
 * Created by 廖师兄
 * 2017-07-30 16:22
 */
public interface RedisConstant {

    String TOKEN_PREFIX = "token_%s";

    Integer EXPIRE = 7200; //2小时

    String LOCK_PREFIX="lock_";
}
