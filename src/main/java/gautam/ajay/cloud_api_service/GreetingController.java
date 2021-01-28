package gautam.ajay.cloud_api_service;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    private final List<Greeting> greetings = new LinkedList<>();

    @GetMapping("/greeting")
    public List<Greeting> greeting(@RequestParam(value = "name", defaultValue = "Batman") String name) {
        if (greetings.size() > 25) {
            greetings.remove(greetings.size() - 1);
        }
        greetings.add(0, new Greeting(counter.incrementAndGet(), String.format(template, name)));
        return greetings;
    }
}
