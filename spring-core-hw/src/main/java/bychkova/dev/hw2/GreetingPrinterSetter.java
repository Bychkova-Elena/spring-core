package bychkova.dev.hw2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GreetingPrinterSetter {

    private GreetingService greetingService;

    @Autowired
    public void setGreetingService(@Qualifier("friendlyGreetingService") GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void print(String name) {
        System.out.println(greetingService.greet(name));
    }
}
