package bychkova.dev.hw2;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GreetingPrinterConstructor {
    private final GreetingService greetingService;

    public GreetingPrinterConstructor(@Qualifier("formalGreetingService") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void print(String name) {
        System.out.println(greetingService.greet(name));
    }
}
