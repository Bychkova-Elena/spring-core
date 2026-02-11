package bychkova.dev.hw2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class GreetingPrinterField {

    @Autowired
    @Qualifier("formalGreetingService")
    GreetingService greetingService;

    public void print(String name) {
        System.out.println(greetingService.greet(name));
    }
}
