package com.sennan.server.aspect;

import com.sennan.common.context.AutoFillConstant;
import com.sennan.common.context.BaseContext;
import com.sennan.common.enumeration.OperationType;
import com.sennan.server.annotation.AutoFill;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 自定义切面类，实现公共字段自动填充处理逻辑
 */
@Aspect
@Component

public class AutoFillAspect {

    /**
     * 指定切入点
     * 即对那些类的那些方法进行拦截
     */
    //execution这里面表示的是锁定了mapper包下的所有类和所有方法，
    //@annotation里面表示的是带有AutoFill注解的
    @Pointcut("@annotation(com.sennan.server.annotation.AutoFill)")
    public void autoFillPointCut() {
    }

    /**
     * 前置通知，在通知中进行公共字段的赋值
     * JoinPoint：表示连接点
     */
    @Before("autoFillPointCut()")
    public void autoFill(JoinPoint joinPoint) {


        //获取当前被拦截的方法上的数据库的操作类型(这都是反射包里面的代码)
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();   //方法签名对象
        AutoFill autoFill = signature.getMethod().getAnnotation(AutoFill.class);  //后的方法上的注释
        OperationType operationType = autoFill.value();  //获得数据库操作类型

        //获取当前被拦截的方法的参数---->实体对象
        Object[] args = joinPoint.getArgs();
        //判断是否有参数
        if (args == null || args.length == 0) {
            return;
        }
        //获取出实体对象，一定要用Object，因为后期不一定是一个具体的实体对象
        Object entity = args[0];

        //为获取到的实体对象中的公共属性进行准备赋值数据
        LocalDateTime now = LocalDateTime.now();
        String currentName = BaseContext.getCurrentName();

        //根据当前不同的操作类型，为对应的属性通过反射赋值
        if (operationType == OperationType.INSERT) {
            //四个公共字段赋值
            try {
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, String.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, String.class);
                //通过反射为对象属性赋值
                setCreateTime.invoke(entity, now);
                setCreateUser.invoke(entity, currentName);
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentName);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (operationType == OperationType.UPDATE) {
            //为两个字段赋值
            try {
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, String.class);

                //通过反射为对象属性赋值
                setUpdateTime.invoke(entity, now);
                setUpdateUser.invoke(entity, currentName);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}

