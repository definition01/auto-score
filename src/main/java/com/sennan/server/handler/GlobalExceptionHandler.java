package com.sennan.server.handler;

import com.sennan.common.constant.MessageConstant;
import com.sennan.common.exception.BaseException;
import com.sennan.common.result.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public ApiResponse exceptionHandler(BaseException ex){

        return ApiResponse.error(ex.getMessage());
    }

    /*
     * 处理SQL异常
     * @param ex
     * @return
     * */
    @ExceptionHandler
    public ApiResponse exceptionHandler(SQLIntegrityConstraintViolationException ex){
        //获得异常信息
        String message = ex.getMessage();
        if(message.contains("Duplicate entry")){
            //根据空格截取已经存在的用户名
            String[] split = message.split(" ");
            String username = split[2];
            //拼接错误的提示语句
            String msg = username + MessageConstant.ALREADY_EXISTS;
            return ApiResponse.error(msg);
        }else {
            return ApiResponse.error(MessageConstant.UNKNOWN_ERROR);
        }

    }

}
