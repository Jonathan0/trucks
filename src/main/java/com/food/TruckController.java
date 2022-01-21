package com.food;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class TruckController {

    private final TruckRepository repository;

    TruckController(TruckRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/")
    public String index() {
        return "Greetings from Spring Boot, Trucks!";
    }

    @GetMapping("/trucks")
    public List<Truck> all() {
        return repository.findAll();
    }

    @GetMapping("/trucks/{id}")
    public Truck one(@PathVariable Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new TruckNotFoundException(id));
    }

    @PostMapping("/trucks")
    public Truck newEmployee(@RequestBody Truck newTruck) {
        return repository.save(newTruck);
    }

    @PutMapping("/trucks/{id}")
    public Truck replaceTruck(@RequestBody Truck newTruck, @PathVariable Long id) {

        return repository.findById(id)
                .map(truck -> {
                    truck.setDayOrder(newTruck.getDayOrder());
                    truck.setDayOfWeekStr(newTruck.getDayOfWeekStr());
                    truck.setStarttime(newTruck.getStarttime());
                    truck.setEndtime(newTruck.getEndtime());
                    return repository.save(truck);
                })
                .orElseGet(() -> {
                    newTruck.setId(id);
                    return repository.save(newTruck);
                });
    }

    @DeleteMapping("/trucks/{id}")
    void deleteTruck(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
