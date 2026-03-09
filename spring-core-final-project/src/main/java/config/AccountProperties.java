package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:application.properties")
public class AccountProperties {

    public final int defaultAmount;
    public final double transferCommission;

    public AccountProperties(
            @Value("${account.default-amount}") int defaultAmount,
            @Value("${account.transfer-commission}") double transferCommission
    ) {
        this.defaultAmount = defaultAmount;
        this.transferCommission = transferCommission;
    }
}
