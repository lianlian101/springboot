package com.test.springboot.general.aop;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;


@Aspect
@Component
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class CustomAspect {
    
    private static Logger logger = LoggerFactory.getLogger(CustomAspect.class);

    @Pointcut("execution(* com.test.springboot.controller.*.*(..))")
    public void aspect() {

    }

    @Before("aspect()")
    public void before(JoinPoint point) {
        String className = point.getTarget().getClass().getName();
        String method = point.getSignature().getName();
        String args = StringUtils.join(point.getArgs(), ",");
        logger.info("类名[{}] -- 方法[{}] -- 参数[{}]", className, method, args);
    }
    

}
