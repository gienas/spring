package pl.ene.springbootrestjpa.metrics;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CustomMetricListAllCustomers {

    private Counter counter;

    public CustomMetricListAllCustomers(MeterRegistry meterRegistry) {
        counter = Counter.builder("custom_list_all_customers_count")
                .description("Listing all customers")
                .tags("environment", "development")
                .register(meterRegistry);
    }

    public void incrementCustomMetric() {
        counter.increment();
    }
}
