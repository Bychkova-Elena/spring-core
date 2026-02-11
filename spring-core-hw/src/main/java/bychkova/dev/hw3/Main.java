package bychkova.dev.hw3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext("bychkova.dev.hw3");

        JobRunner jobRunner = context.getBean(JobRunner.class);
        JobRunner jobRunner2 = context.getBean(JobRunner.class);

        jobRunner.runOnce();
        jobRunner2.runOnce();

    }
}
