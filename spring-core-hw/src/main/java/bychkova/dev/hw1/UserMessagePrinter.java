package bychkova.dev.hw1;

public class UserMessagePrinter {
    private final UserMessageService userMessageService;

    public UserMessagePrinter(UserMessageService userMessageService) {
        this.userMessageService = userMessageService;
    }

    public void printMessage(String name) {
        String msg = userMessageService.createMessage(name);
        System.out.println(msg);
    }
}
