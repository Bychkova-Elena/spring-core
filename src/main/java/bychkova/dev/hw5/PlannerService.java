package bychkova.dev.hw5;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PlannerService {

    private final boolean enabled;
    private final PlannerProperties plannerProperties;

    public PlannerService(@Value("${planner.enabled: false}") boolean enabled,
                          PlannerProperties plannerProperties
    ) {
        this.enabled = enabled;
        this.plannerProperties = plannerProperties;
    }

    public void printConfig() {
        System.out.println("enabled: " + enabled);
        System.out.println("batchSize: " + plannerProperties.batchSize);
        System.out.println("defaultDuration: " + plannerProperties.defaultDuration);
    }
}
