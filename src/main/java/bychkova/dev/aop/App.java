package bychkova.dev.aop;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.UUID;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        User user = context.getBean(User.class);

        UUID id = user.getUserId();
        user.updateName(id, null);

//        User user2 = new User();
//        UUID id2 = user2.getUserId();
//        user.updateName(id2, null); // не обернуто proxy, ошибка не выведется

        user.updateName(id, "Olga");
        user.sayHelloToPerson(null);
    }
}
