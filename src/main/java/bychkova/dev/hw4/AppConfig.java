package bychkova.dev.hw4;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    public AppLogger appLogger() {
        return new AppLogger();
    }

    @Bean
//    @Scope("prototype")
    public OperationContext operationContext() {
        return new OperationContext();
    }
}
