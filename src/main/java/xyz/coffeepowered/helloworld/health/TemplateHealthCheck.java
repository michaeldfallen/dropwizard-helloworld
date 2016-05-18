package xyz.coffeepowered.helloworld.health;

import com.codahale.metrics.health.HealthCheck;
import xyz.coffeepowered.helloworld.HelloWorldConfiguration;

public class TemplateHealthCheck extends HealthCheck {
    private final String template;

    public static TemplateHealthCheck fromConfig(
            HelloWorldConfiguration config
    ) {
        return new TemplateHealthCheck(config.getTemplate());
    }

    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("Template doesn't include a name.");
        } else {
            return Result.healthy();
        }
    }
}
