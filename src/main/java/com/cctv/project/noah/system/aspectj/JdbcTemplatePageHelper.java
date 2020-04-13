package com.cctv.project.noah.system.aspectj;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

/**
 * @author by yanhao
 * @Classname JdbcTemplatePageHelper
 * @Description TODO
 * @Date 2019/9/24 4:03 下午
 */
@Aspect
@Component
public class JdbcTemplatePageHelper {

    // 配置织入点
    @Pointcut("execution(* org.springframework.jdbc.core.namedparam.*.*(..))")
    public void queryCut() {
    }

    @Before("queryCut()")
    public void doBefore(JoinPoint joinPoint) {
        handleSql(joinPoint,null);
    }

    /**
     * 处理完请求后执行
     *
     * @param joinPoint 切点
     */
    @AfterReturning("queryCut()")
    public void doAfterReturning(JoinPoint joinPoint) {
        handleSql(joinPoint,null);
    }

    protected void handleSql(final JoinPoint joinPoint, final Exception e) {
            Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramValues.length; i++) {
            paramValues[0]="123";
        }

//        for(int i=0;i<paramNames.length;i++){
//            String key=paramNames[i];
//            if(key.equals("sql")){
//                paramValues[i]="select * from table";
//            }
//            System.out.println(paramNames[i]+","+paramValues[i]);
//        }
        System.out.println(joinPoint);
    }


}
