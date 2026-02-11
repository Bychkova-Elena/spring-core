package bychkova.dev.hw4;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LifecyclePlaygroundApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        OperationContext operationContext1 = context.getBean(OperationContext.class);
        OperationContext operationContext2 = context.getBean(OperationContext.class);
        OperationContext operationContext3 = context.getBean(OperationContext.class);

        System.out.println(operationContext1 == operationContext3);

        AppLogger appLogger1 = context.getBean(AppLogger.class);
        AppLogger appLogger2 = context.getBean(AppLogger.class);

        System.out.println(appLogger1 == appLogger2);

        context.close();
    }
}
