package airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import airline.controller.model.AircraftData;
import airline.service.AircraftService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/aircrafts")
@Slf4j
@Data
public class AircraftController {

    private AircraftService aircraftService;
    
    @Autowired
    public AircraftController(AircraftService aircraftService) {
    	this.aircraftService = aircraftService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AircraftData createAircraft(@RequestBody AircraftData aircraftData) {
    	log.info("Creating a Aircraft {}", aircraftData);
        return aircraftService.createAircraft(aircraftData);
    }

    @GetMapping("/{id}")
    public AircraftData getAircraft(@PathVariable Long id) {
    	log.info("Retrieving Aircraft with ID={}", id);
        return aircraftService.getAircraft(id);
    }

    @GetMapping
    public List<AircraftData> getAllAircrafts() {
    	log.info("Retrieving all Aircraft");
        return aircraftService.getAllAircrafts();
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateAircraft(@PathVariable Long id, 
    		@RequestBody AircraftData aircraftData) {
    	log.info("Updating Aircraft {}", aircraftData);
    	aircraftService.updateAircraft(id, aircraftData);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAircraftById(@PathVariable Long id) {
    	log.info("Deleting Aircraft with ID={}", id);
        aircraftService.deleteAircraftById(id);
    }
}
