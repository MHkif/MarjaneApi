package yc.mhkif.marjaneapi.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private final CustomLogger customLogger;

    @Autowired
    public LoggingAspect(CustomLogger customLogger) {
        this.customLogger = customLogger;
    }

    @Pointcut("execution(* yc.mhkif.marjaneapi.Controllers.PromotionController.*(..))")
    public void pointcut() {
    }

    @AfterReturning(value = "pointcut()", returning = "returnValue")
    public void afterReturningAdvice(JoinPoint joinPoint, Object returnValue) {
        String methodName = joinPoint.getSignature().getName();
        String action = "Action performed in method: " + methodName;

        customLogger.logAction(methodName, action);
        customLogger.closeLogger();
    }
}
