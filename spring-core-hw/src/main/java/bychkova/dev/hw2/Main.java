package bychkova.dev.hw2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(GreetConfig.class);

        GreetingPrinterConstructor greetingPrinterConstructor = context.getBean(GreetingPrinterConstructor.class);
        GreetingPrinterField greetingPrinterField = context.getBean(GreetingPrinterField.class);
        GreetingPrinterSetter greetingPrinterSetter = context.getBean(GreetingPrinterSetter.class);

        greetingPrinterConstructor.print("Pasha");
        greetingPrinterField.print("Pasha");
        greetingPrinterSetter.print("Pasha");
    }
}
