package airline.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import airline.controller.model.AircraftData;
import airline.controller.model.AirlineData;
import airline.controller.model.EmployeeData;
import airline.service.ProjectService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/airline")
@Slf4j
public class ProjectController {
	
	@Autowired
    private ProjectService projectService;

    @GetMapping
	public ResponseEntity<List<AirlineData>> retrieveAllAirlines() {
	    List<AirlineData> airlines = projectService.retrieveAllAirlines();
	    if (airlines.isEmpty()) {
	        log.info("No airlines found in the database");
	        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(airlines);
	    } else {
	        log.info("Retrieved all airlines");
	        return ResponseEntity.ok(airlines);
	    }
	}

	@GetMapping("/airline")
	public AirlineData retrieveAirlineById(@PathVariable Long airlineId) {
	    log.info("Retrieving Airline with ID={}", airlineId);
	    return projectService.retrieveAirlineById(airlineId);
	}

	@PostMapping("/airline")
    @ResponseStatus(code = HttpStatus.CREATED)
    public AirlineData insertAirline(@RequestBody AirlineData airlineData) {
        log.info("Creating Airline {}", airlineData);
        return projectService.saveAirline(airlineData);
    }

    @PutMapping("/airline/{airlineId}")
	public AirlineData updateAirline(@PathVariable Long airlineId, @RequestBody AirlineData airlineData) {
	    airlineData.setAirlineId(airlineId);
	    log.info("Updating Airline {}", airlineData);
	    return projectService.saveAirline(airlineData);
	}

	@DeleteMapping("/{airlineId}")
	public Map<String, String> deleteAirlineById(@PathVariable Long airlineId) {
	    log.info("Deleting airline with ID={}", airlineId);
	    projectService.deleteAirlineById(airlineId);
	    return Map.of("message", "Deletion of airline with ID=" + airlineId + " was successful.");
	}

	@PostMapping("/{airlineId}/aircraft")
	@ResponseStatus(code = HttpStatus.CREATED)
    public AircraftData insertAircraft(@PathVariable Long airlineId, @RequestBody AircraftData aircraftData) {
        log.info("Adding Aircraft {} to Airline with ID={}", aircraftData, airlineId);
        return projectService.saveAircraft(airlineId, aircraftData);
    }

	@PostMapping("/{airlineId}/aircraft/{aircraftId}/employee")
	@ResponseStatus(code = HttpStatus.CREATED)
	public EmployeeData insertEmployee(@PathVariable Long airlineId, @PathVariable Long aircraftId,
	        @RequestBody EmployeeData employeeData) {
	    employeeData.setAircraftId(aircraftId);
	    
	    log.info("Adding Employee {} to Airline with ID={} and Aircraft ID={}", 
	            employeeData, airlineId, aircraftId);
	    
	    return projectService.saveEmployee(airlineId, aircraftId, employeeData);
	}

//
	@PutMapping("/{airlineId}/aircraft/{aircraftId}")
	public AircraftData updateAircraft(@PathVariable Long airlineId, @PathVariable Long aircraftId, 
	        @RequestBody AircraftData aircraftData) {
	    aircraftData.setAircraftId(aircraftId);
	    log.info("Updating aircraft with ID={} from Airline with ID={}", aircraftId, airlineId);
	    return projectService.saveAircraft(airlineId, aircraftData);
	}
//	
	@PutMapping("/{airlineId}/{aircraftId}/employee/{employeeId}")
	public EmployeeData updateEmployee(@PathVariable Long airlineId, @PathVariable Long employeeId,
			@PathVariable Long aircraftId, @RequestBody EmployeeData employeeData) {
	    employeeData.setEmployeeId(employeeId);
	    log.info("Updating employee with ID={} from Airline with ID={} and Aircraft ID={}",
	            employeeId, airlineId, aircraftId);
	    return projectService.saveEmployee(airlineId, aircraftId, employeeData);
	}
//
	@GetMapping("/{airlineId}/aircraft")
	public List<AircraftData> retrieveAllAircraft(@PathVariable Long airlineId) {
		log.info("Retrieving all aircraft of Airline with ID={}", airlineId);
		return projectService.retrieveAllAircraft();
	}
	
    @GetMapping("/{airlineId}/employee")
	public List<EmployeeData> retrieveAllEmployees(@PathVariable Long airlineId) {
	    log.info("Retrieving all employees of Airline with ID={}", airlineId);
	    return projectService.retrieveAllEmployees();
	}

    @GetMapping("/{airlineId}/aircraft/{aircraftId}")
    public AircraftData retrieveAircraftById(@PathVariable Long airlineId, @PathVariable Long aircraftId) {
    	log.info("Retrieving aircraft with ID={} from Airline with ID={}", aircraftId, airlineId);
    	return projectService.retrieveAircraftById(aircraftId);
    }

    @GetMapping("/{airlineId}/employee/{employeeId}")
	public EmployeeData retrieveEmployeeById(@PathVariable Long airlineId, @PathVariable Long employeeId) {
	    log.info("Retrieving employee with ID={} from Airline with ID={}", employeeId, airlineId);
	    return projectService.retrieveEmployeeById(employeeId);
	}

    @DeleteMapping("/{airlineId}/aircraft/{aircraftId}")
    public Map<String, String> deleteAircraftById(@PathVariable Long airlineId, @PathVariable Long aircraftId) {
    	log.info("Deleting aircraft with ID={}", aircraftId);
    	projectService.deleteAircraftById(aircraftId);
    	return Map.of("message", "Deletion of aircraft with ID=" + aircraftId + " was successful.");
    }
    
    @DeleteMapping("/{airlineId}/employee/{employeeId}")
    public Map<String, String> deleteEmployeeById(@PathVariable Long airlineId, @PathVariable Long employeeId) {
        log.info("Deleting employee with ID={} from Airline with ID={}", employeeId, airlineId);
        projectService.deleteEmployeeById(employeeId);
        return Map.of("message", "Deletion of employee with ID=" + employeeId + " was successful.");
    }

}
