package bychkova.dev.hw4;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class AppLogger {

    public AppLogger() {
        System.out.println("[AppLogger] constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("AppLogger PostConstruct");
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("AppLogger PreDestroy");
    }
}
