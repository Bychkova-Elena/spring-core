package bychkova.dev.hw2;

public class FriendlyGreetingService implements GreetingService{
    @Override
    public String greet(String name) {
        return "Привет, " + name + "!";
    }
}
