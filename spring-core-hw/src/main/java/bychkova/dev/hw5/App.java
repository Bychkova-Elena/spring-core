package bychkova.dev.hw5;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("bychkova.dev.hw5");

        PlannerService plannerService = context.getBean(PlannerService.class);
        plannerService.printConfig();

        context.close();
    }
}
