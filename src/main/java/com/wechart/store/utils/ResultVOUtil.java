package com.wechart.store.utils;

import com.wechart.store.constants.ResultVOConstant;
import com.wechart.store.enums.ResultStatusEnum;
import com.wechart.store.vo.ResultVO;

/**
 * All rights Reserved, Designed By hxjd
 *
 * @类名: ResultVOUtil
 * @包名: com.wechart.store.utils
 * @描述: 用于生成返回结果
 * @日期: 2018/5/21 21:57
 */
public class ResultVOUtil {
    public static ResultVO success(Object o){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultStatusEnum.SUCCESS.getCode());
        resultVO.setMessage(ResultVOConstant.SUCCESS_RESULT);
        resultVO.setData(o);
        return resultVO;
    }

    public static ResultVO success(){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultStatusEnum.SUCCESS.getCode());
        resultVO.setMessage(ResultVOConstant.SUCCESS_RESULT);
        return resultVO;
    }

    public static ResultVO error(Object o){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(ResultStatusEnum.ERROR.getCode());
        resultVO.setMessage(ResultVOConstant.ERROR_RESULT);
        resultVO.setData(o);
        return resultVO;
    }

    public static ResultVO of(Integer code,String message,Object o){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(message);
        resultVO.setData(o);
        return resultVO;
    }

}
