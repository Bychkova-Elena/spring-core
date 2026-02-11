package bychkova.dev.hw2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "bychkova.dev.hw2")
public class GreetConfig {

    @Bean
    public FormalGreetingService formalGreetingService() {
        return new FormalGreetingService();
    }

    @Bean
    public FriendlyGreetingService friendlyGreetingService() {
        return new FriendlyGreetingService();
    }
}
