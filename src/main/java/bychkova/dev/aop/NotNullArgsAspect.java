package bychkova.dev.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class NotNullArgsAspect {

//    @Before("@annotation(NotNullArgs)")
//    public void notNullArgsBefore(JoinPoint joinPoint) {
//        for (Object arg : joinPoint.getArgs()) {
//            if (arg == null) {
//                throw new IllegalArgumentException();
//            }
//        }
//    }

    @Around("@annotation(NotNullArgs)")
    public Object notNullArgsAround(ProceedingJoinPoint pjp) {
        for (Object arg : pjp.getArgs()) {
            if (arg == null) {
                throw new IllegalArgumentException();
            }
        }

        try {
            return pjp.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            System.out.printf("Метод %s завершился\n", pjp.getSignature().getName());
        }
    }
}
