package com.wechart.store.service.impl;

import com.wechart.store.constants.RedisConstant;
import com.wechart.store.service.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: RedisLockImpl
 * @包名: com.imooc.service.impl
 * @描述: redis实现的分布式锁
 * @日期: 2018/6/2 16:34
 */
@Service
@Slf4j
public class RedisLockImpl implements RedisLock {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public String lock(String lockName, Integer requireTime, Integer expireTime) {
        lockName= RedisConstant.LOCK_PREFIX+lockName;
        String requiredId = UUID.randomUUID().toString();
        //获取锁结束时间
        long end = System.currentTimeMillis() + requireTime;
        while(System.currentTimeMillis()<end){
            //setNX
            if(redisTemplate.opsForValue().setIfAbsent(lockName,requiredId)){
                //设置超时时间，不过在这里设置当程序异常时无法给锁设置时间，要在后面判断
                redisTemplate.expire(lockName, expireTime, TimeUnit.MILLISECONDS);
                return requiredId;
            }

            //判断是否设置了过期时间
            if(redisTemplate.getExpire(lockName)<=0){
                redisTemplate.expire(lockName, expireTime, TimeUnit.MILLISECONDS);
            }
            //睡眠
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
        return null;
    }

    @Override
    public boolean unlock(String lockName, String requireId) {
        lockName=RedisConstant.LOCK_PREFIX+lockName;
        //是否成功释放锁
        /*boolean flag=false;
        while(true){
            redisTemplate.watch(lockName);
            if(!requireId.equals(redisTemplate.opsForValue().get(lockName))) {
                //开启事务
                redisTemplate.multi();
                redisTemplate.delete(lockName);
                List<Object> exec = redisTemplate.exec();
                if (exec == null) {
                    continue;
                }
                flag=true;
            }
            redisTemplate.unwatch();
            break;
        }
        return flag;*/
        String currentValue = redisTemplate.opsForValue().get(lockName);
        if(!StringUtils.isEmpty(currentValue)&&currentValue.equals(requireId)){
            return redisTemplate.opsForValue().getOperations().delete(lockName);
        }
        return false;
    }
}
