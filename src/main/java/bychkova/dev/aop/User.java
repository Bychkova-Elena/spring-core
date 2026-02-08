package bychkova.dev.aop;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class User{

    private final UUID userId;
    private String name;

    public User() {
        this.userId = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public UUID getUserId() {
        return userId;
    }

    @NotNullArgs
    public void updateName(UUID userId, String name) {
        if (this.userId.equals(userId)) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("User is mismatch");
        }
    }

    public void sayHelloToPerson(String name) {
        System.out.println("User say hello to " + name);
    }
}
