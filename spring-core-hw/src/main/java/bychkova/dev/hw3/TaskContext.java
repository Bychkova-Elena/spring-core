package bychkova.dev.hw3;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Scope("prototype")
public class TaskContext {
    private final UUID id;

    public TaskContext() {
        this.id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
    }
}
