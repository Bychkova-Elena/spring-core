package bychkova.dev.hw3;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
public class JobRunner {
    private final ObjectProvider<TaskContext> taskContextProvider;


    public JobRunner(ObjectProvider<TaskContext> taskContextProvider) {
        this.taskContextProvider = taskContextProvider;
    }

    public void runOnce() {
        TaskContext taskContext = taskContextProvider.getObject();
        System.out.println(taskContext.getId());
    }
}
