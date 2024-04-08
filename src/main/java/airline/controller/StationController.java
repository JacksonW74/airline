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

import airline.controller.model.StationData;
import airline.service.StationService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/stations")
@Slf4j
@Data
public class StationController {

    private StationService stationService;
    
    @Autowired
    public StationController(StationService stationService) {
    	this.stationService = stationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StationData createStation(@RequestBody StationData stationData) {
    	log.info("Creating a Station {}", stationData);
        return stationService.createStation(stationData);
    }

    @GetMapping("/{id}")
    public StationData getStation(@PathVariable Long id) {
    	log.info("Retrieving Station with ID={}", id);
        return stationService.getStation(id);
    }

    @GetMapping
    public List<StationData> getAllStations() {
    	log.info("Retrieving all Station");
        return stationService.getAllStations();
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateStation(@PathVariable Long id, 
    		@RequestBody StationData stationData) {
    	log.info("Updating Station {}", stationData);
    	stationService.updateStation(id, stationData);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteStationById(@PathVariable Long id) {
    	log.info("Deleting Station with ID={}", id);
        stationService.deleteStationById(id);
    }
}
