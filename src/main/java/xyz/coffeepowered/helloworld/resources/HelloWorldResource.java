package xyz.coffeepowered.helloworld.resources;

import com.codahale.metrics.annotation.Timed;
import xyz.coffeepowered.helloworld.HelloWorldConfiguration;
import xyz.coffeepowered.helloworld.api.Saying;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private final String template;
    private final String defaultName;
    private final AtomicLong counter;

    public static HelloWorldResource fromConfig(
            HelloWorldConfiguration config
    ) {
        return new HelloWorldResource(
                config.getTemplate(),
                config.getDefaultName()
        );
    }

    public HelloWorldResource(
            String template,
            String defaultName
    ) {
        this.template = template;
        this.defaultName = defaultName;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed
    public Saying sayHello(
            @QueryParam("name") Optional<String> name
    ) {
        final String value = String.format(template, name.orElse(defaultName));
        return new Saying(counter.incrementAndGet(), value);
    }
}
