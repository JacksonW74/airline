package airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
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
import airline.entity.Airline;
import airline.service.AirlineService;
import airline.service.AircraftService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/aircraft")
@Slf4j
@Data
public class AircraftController {

    private AircraftService aircraftService;
    // adding AirlineService to add the airlineId to the aircraft table
    private AirlineService airlineService;
    
    @Autowired
    public AircraftController(AircraftService aircraftService, AirlineService airlineService) {
        this.aircraftService = aircraftService;
        this.airlineService = airlineService; // Injecting AirlineService
    }


//    @PostMapping
//    @ResponseStatus(HttpStatus.CREATED)
//    public AircraftData createAircraft(@RequestBody AircraftData aircraftData) {
//    	log.info(""Creating Aircraft {} for Airline with ID {}", aircraftData);
//        return aircraftService.createAircraft(aircraftData);
//    }

    //	second option for createAircraft
    
    @PostMapping("/{airlineId}") 
    @ResponseStatus(HttpStatus.CREATED)
    public AircraftData createAircraft(@PathVariable Long airlineId, @RequestBody AircraftData aircraftData) {
        log.info("Creating Aircraft {} for Airline with ID {}", aircraftData, airlineId);
        
        Airline airline = airlineService.findAirlineById(airlineId); 
        return aircraftService.createAircraft(aircraftData, airline); 
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
