package xyz.coffeepowered.helloworld;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import xyz.coffeepowered.helloworld.health.TemplateHealthCheck;
import xyz.coffeepowered.helloworld.resources.HelloWorldResource;

public class HelloWorldApplication
        extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {

    }

    public void run(
            HelloWorldConfiguration config,
            Environment environment) {
        environment.healthChecks().register(
                "template",
                TemplateHealthCheck.fromConfig(config)
        );
        environment.jersey().register(HelloWorldResource.fromConfig(config));
    }
}
