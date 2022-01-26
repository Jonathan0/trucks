package com.food;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.*;

import static com.utils.GetTrucks.calculateDistance;

@RestController
public class TruckController {

    @Autowired
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
    public Truck oneTruck(@PathVariable Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new TruckNotFoundException(id));
    }

    @PostMapping("/trucks")
    public Truck newTruck(@RequestBody Truck newTruck) {
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

    @GetMapping("/trucks/find")
    public List<Truck> findTruck(
            @RequestParam(value = "latitude", defaultValue = "0.0") String latitude,
            @RequestParam(value = "longitude", defaultValue = "0.0") String longitude,
            @RequestParam(value = "count", defaultValue = "5") int count) throws RuntimeException {

        List<Truck> allTrucks = repository.findAll();
        Map<Double, Long> truckMap = new TreeMap<>();
        double lat = Double.parseDouble(latitude);
        double lon = Double.parseDouble(longitude);

        for(Truck t : allTrucks) {
            double dis = calculateDistance(lat, lon, t.getLatitude(), t.getLongtitude());
            t.setDistance(dis);
            // we can enable to update all trucks distance, but it is very expensive.
            // repository.findById(t.getId()).map(truck -> repository.save(truck));
            truckMap.put(dis, t.getId());
        }

        List<Long> topFiveIds = new ArrayList<>();
        int n = count;
        for(Double key : truckMap.keySet()) {
            Long id = truckMap.get(key);
            topFiveIds.add(id);
            if(--n <= 0){
                break;
            }
        }

        // so the repository has build-in Sort function, but it is much slower.
        // return repository.findAll(Sort.by("distance").ascending());
        return repository.findAllById(topFiveIds);
    }

}
