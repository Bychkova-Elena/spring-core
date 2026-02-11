package bychkova.dev.aop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@ComponentScan(basePackages = "bychkova.dev.aop")
@EnableAspectJAutoProxy
public class AppConfig {
}
