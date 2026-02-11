package bychkova.dev.hw4;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class OperationContext {

    public OperationContext() {
        System.out.println("[OperationContext] constructor");
    }

    @PostConstruct
    public void init() {
        System.out.println("OperationContext PostConstruct");
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("OperationContext PreDestroy");
    }
}
