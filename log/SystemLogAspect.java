package com.lc.lcserve.log;

import com.lc.lcserve.entity.Log;
import com.lc.lcserve.mapper.LogMapper;
import com.lc.lcserve.model.InterfaceType;
import com.lc.lcserve.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Spring AOP实现日志管理
 */
@Aspect
@Component
@Slf4j
public class SystemLogAspect {
 
    private static final ThreadLocal<Date> beginTimeThreadLocal = new NamedThreadLocal<Date>("ThreadLocal beginTime");

 
    @Autowired(required = false)
    private HttpServletRequest request;

    @Resource
    private LogMapper logMapper;
 
    /**
	 * 定义切面，只置入带 @SystemLog 注解的方法或类 
     * Controller层切点，注解方式
	 * @Pointcut("execution(* *..controller..*Controller*.*(..))")
     */
    @Pointcut("@annotation(com.lc.lcserve.log.SystemLog)")
    public void controllerAspect() {
 
    }
 
    /**
     * 前置通知 (在方法执行之前返回)用于拦截Controller层记录用户的操作的开始时间
     * @param joinPoint 切点
     * @throws InterruptedException
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint joinPoint) throws InterruptedException{

        Date beginTime=new Date();
        beginTimeThreadLocal.set(beginTime);
    }
 
 
    /**
     * 后置通知(在方法执行之后并返回数据) 用于拦截Controller层无异常的操作
     * @param joinPoint 切点
     */
    @AfterReturning(value = "controllerAspect()",returning = "methodResult")
    public void after(JoinPoint joinPoint, Object methodResult){
        try {
            String username = "";
            ResponseEntity re = (ResponseEntity) methodResult;
            // 方法返回结果
            Result methodResult1 = (Result) re.getBody();


            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            SystemLog declaredAnnotation = signature.getMethod().getDeclaredAnnotation(SystemLog.class);
            String description = declaredAnnotation.description();


            Object[] loginParams = joinPoint.getArgs();
            Log Log = new Log();

            if(description.equals("insert")){
                // 插入
                Log.setInterfaceType(InterfaceType.INSERT.getCode());
            } else if(description.equals("change")){
                Log.setInterfaceType(InterfaceType.CHANGE.getCode());
            } else if(description.equals("delete")){
                Log.setInterfaceType(InterfaceType.DELETE.getCode());
            } else if(description.equals("search")){
                Log.setInterfaceType(InterfaceType.SEARCH.getCode());
            } else {
                Log.setInterfaceType(InterfaceType.LOGIN.getCode());
            }
			Log.setRequestUser(username);
			Log.setDescription(description);
			Log.setInterfaceType(declaredAnnotation.type().getCode());
			Log.setRequestUrl(request.getRequestURI());
			if(methodResult1.getResult() != null && methodResult1.getResult().toString().length() > 200) {
                // 不为空且body长
                Log.setResponseInfo(methodResult1.getResult().toString().substring(0, 200));
            } else if(methodResult1.getResult() != null){
			    // 不为空
                Log.setResponseInfo(methodResult1.getResult().toString());
            }
            //			log.setRequestType(request.getMethod())
            StringBuilder params = new StringBuilder();
            for (int i = 0; i < loginParams.length; i++) {
                params.append(loginParams[i].toString());
            }
			Log.setRequestParams(params.toString());

//			Date logStartTime = beginTimeThreadLocal.get();
            if("success".equals(methodResult1.getMessage())){
                // 成功的日志
                Log.setStatus(0);
            } else {
                Log.setStatus(1);
            }
			long beginTime = beginTimeThreadLocal.get().getTime();
			Log.setHappenTime(beginTime);
			long endTime = System.currentTimeMillis();
			// 耗时
			Long logElapsedTime = endTime - beginTime;
			// 请求耗时
			Log.setElapsedTime(logElapsedTime);
			SaveSystemLog.saveLong(Log, logMapper);
            
        } catch (Exception e) {
            log.error("AOP后置通知异常", e);
        }
    }
 
 
    /**
     * 保存日志至数据库  后期可以改造为多线程或者使用消息队列
     */
    private static class SaveSystemLog {

        public static void saveLong(Log Log, LogMapper logMapper) {
            logMapper.insert(Log);
        }

    }
 
    /**
     * 获取注解中对方法的描述信息 用于Controller层注解
     * @param joinPoint 切点
     * @return 方法描述
     * @throws Exception
     */
    public static Map<String, Object> getControllerMethodInfo(JoinPoint joinPoint) throws Exception{
 
        Map<String, Object> map = new HashMap<String, Object>(16);
        // 获取目标类名
        String targetName = joinPoint.getTarget().getClass().getName();
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 获取相关参数
        Object[] arguments = joinPoint.getArgs();
        // 生成类对象
        Class targetClass = Class.forName(targetName);
        // 获取该类中的方法
        Method[] methods = targetClass.getMethods();
 
        String description = "";
        Integer type = null;
 
        for(Method method : methods) {
            if(!method.getName().equals(methodName)) {
                continue;
            }
            Class[] clazzs = method.getParameterTypes();
            if(clazzs.length != arguments.length) {
                // 比较方法中参数个数与从切点中获取的参数个数是否相同，原因是方法可以重载
                continue;
            }
            description = method.getAnnotation(SystemLog.class).description();
            type = method.getAnnotation(SystemLog.class).type().ordinal();
            map.put("description", description);
            map.put("type", type);
        }
        return map;
    }
 
}