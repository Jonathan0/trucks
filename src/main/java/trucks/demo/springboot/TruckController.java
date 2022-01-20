package trucks.demo.springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TruckController {

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot, Trucks!";
    }

}
