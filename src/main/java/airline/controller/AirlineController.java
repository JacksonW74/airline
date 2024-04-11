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

import airline.controller.model.AirlineData;
import airline.service.AirlineService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/airline")
@Slf4j
@Data
public class AirlineController {

    private AirlineService airlineService;
    
    @Autowired
    public AirlineController(AirlineService airlineService) {
    	this.airlineService = airlineService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AirlineData createAirline(@RequestBody AirlineData airlineData) {
    	log.info("Creating a Airline {}", airlineData);
    	return airlineService.createAirline(airlineData);
    }

    @GetMapping("/{id}")
    public AirlineData getAirline(@PathVariable Long id) {
    	log.info("Retrieving Airline with I{}", id);
        return airlineService.getAirline(id);
    }
    
    @GetMapping
    public List<AirlineData> getAllAirlines() {
    	log.info("Retrieving all Airlines");
    	return airlineService.getAllAirlines();
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateAirline(@PathVariable Long id, 
    		@RequestBody AirlineData airlineData) {
    	log.info("Updating Airline {}", airlineData);
    	airlineService.updateAirline(id, airlineData);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteAirline(@PathVariable Long id) {
    	log.info("Deleting Airline with ID={}", id);
        airlineService.deleteAirline(id);
    }
}
