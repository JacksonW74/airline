package airline.service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import airline.controller.model.AircraftData;
import airline.controller.model.AirlineData;
import airline.controller.model.EmployeeData;
import airline.dao.AircraftDao;
import airline.dao.AirlineDao;
import airline.dao.EmployeeDao;
import airline.entity.Aircraft;
import airline.entity.Airline;
import airline.entity.Employee;
import jakarta.transaction.Transactional;

@Service
public class ProjectService {
	
	@Autowired
    private final AircraftDao aircraftDao;
    private final AirlineDao airlineDao;
    private final EmployeeDao employeeDao;

    public ProjectService(AircraftDao aircraftDao, AirlineDao airlineDao, 
    		EmployeeDao employeeDao) {
        this.aircraftDao = aircraftDao;
        this.airlineDao = airlineDao;
        this.employeeDao = employeeDao;
    }
																							
	public AirlineData saveAirline(AirlineData airlineData) {
		Airline airline = findOrCreateAirline(airlineData.getAirlineId());
		copyAirlineFields(airline, airlineData);
		Airline savedAirline = airlineDao.save(airline);
		return new AirlineData(savedAirline);
	}

	@Transactional
	public Airline getAirlineById(Long id) {
		return airlineDao.findById(id)
				.orElseThrow(() -> new NoSuchElementException
					("Airline not found with ID: " + id));
	}

	@Transactional 
	public ResponseEntity<String> deleteAirlineById(Long id) {
		Airline airline = airlineDao.findById(id)
			.orElseThrow(() -> new NoSuchElementException
					("Airline not found with ID: " + id));
			airlineDao.delete(airline);
		return ResponseEntity.ok().body("airline with ID " + id + 
				" deleted successfully");
	}

	@Transactional
	public List<AirlineData> retrieveAllAirlines() {
		List<Airline> airlines = airlineDao.findAll();
		return airlines.stream()
				.map(AirlineData::new)
				.collect(Collectors.toList());
	}

	@Transactional
	public List<AirlineData> getAllAirlines() {
	List<Airline> airlines = airlineDao.findAll();
		return airlines.stream()
			.map(AirlineData::new)
			.collect(Collectors.toList());
	}

	@Transactional
	public AirlineData retrieveAirlineById(Long airlineId) {
		Airline airline = airlineDao.findById(airlineId)
			.orElseThrow(() -> new NoSuchElementException
				("Airline not found with ID: " + airlineId));
		return new AirlineData(airline);
	}

	private void copyAirlineFields(Airline airline, AirlineData airlineData) {
		airline.setName(airlineData.getName());
		airline.setAddress(airlineData.getAddress());
		airline.setCity(airlineData.getCity());
		airline.setState(airlineData.getState());
		airline.setZip(airlineData.getZip());
		airline.setPhone(airlineData.getPhone());
	}

	private Airline findOrCreateAirline(Long airlineId) {
		if (airlineId != null) {
			return airlineDao.findById(airlineId)
				.orElseThrow(() -> new NoSuchElementException
					("Airline not found with ID: " + airlineId));
			} else {
			return new Airline(); 
		}
	}

	@Transactional
	public Airline findAirlineById(Long airlineId) {
		return airlineDao.findById(airlineId)
				.orElseThrow(() -> new NoSuchElementException
						("Airline not found with ID: " + airlineId));
	}

	@Transactional
	public AircraftData saveAircraft(Long airlineId, AircraftData aircraftData) {
	    Airline airline = getAirlineById(airlineId);
	    Long aircraftId = aircraftData.getAircraftId();
	    Aircraft aircraft = findOrCreateAircraft(airlineId, aircraftId);
	    
	    copyAircraftFields(aircraft, aircraftData);
	    
	    aircraft.setAirline(airline);
	    airline.getAircraft().add(aircraft);	
	    
	    Aircraft dbaircraft = aircraftDao.save(aircraft);
	    
	    return new AircraftData(dbaircraft);
	}

	@Transactional
	public EmployeeData saveEmployee(Long airlineId, Long aircraftId, 
			EmployeeData employeeData) {
	    Airline airline = getAirlineById(airlineId);
	    Aircraft aircraft = getAircraftById(aircraftId);
	    Long employeeId = employeeData.getEmployeeId();
	    Employee employee = findOrCreateEmployee(airlineId,aircraftId, employeeId);
	    
	    copyEmployeeFields(employee, employeeData);
	    //4/17/1300
	    employee.setAircraft(aircraft);
	    //
	    if (employee.getAirlines() == null) {
	        employee.setAirlines(new HashSet<>());
	    }
	    
	    employee.getAirlines().add(airline); 	
	    
	    Employee dbemployee = employeeDao.save(employee);
	    return new EmployeeData(dbemployee);
	}
	
	private void copyAircraftFields(Aircraft aircraft, AircraftData aircraftData) {
			aircraft.setManufacturer(aircraftData.getManufacturer());
			aircraft.setModel(aircraftData.getModel());
			aircraft.setTailNumber(aircraftData.getTailNumber());
			aircraft.setNumberOfEngines(aircraftData.getNumberOfEngines());
			aircraft.setPropulsionType(aircraftData.getPropulsionType());
	}
	
	private void copyEmployeeFields(Employee employee, EmployeeData employeeData) {
		employee.setFirstName(employeeData.getFirstName());
		employee.setLastName(employeeData.getLastName());
		employee.setEmail(employeeData.getEmail());
		employee.setJobTitle(employeeData.getJobTitle());
	}

	private Aircraft findOrCreateAircraft(Long airlineId, Long aircraftId) {
		    if (aircraftId == null)
		        return new Aircraft();
		    else
		        return findAircraftById(airlineId, aircraftId);
	}
	
	private Employee findOrCreateEmployee(Long airlineId, Long aircraftId,
			Long employeeId) {
		if (employeeId == null)
			return new Employee();
		else
			return findEmployeeById(airlineId, aircraftId, employeeId);
	}

	@Transactional
	private Aircraft findAircraftById(Long airlineId, Long aircraftId) {
	    Aircraft aircraft = aircraftDao.findById(aircraftId)
	            .orElseThrow(() -> new NoSuchElementException
	            		("Aircraft not found with ID: " + aircraftId));

	    if (aircraft.getAirline().getAirlineId() != airlineId) {
	    	throw new IllegalArgumentException
	    	("This aircraft is not associated with airline with ID " + airlineId);
	    }
	    return aircraft;
	}
	
	@Transactional
	private Employee findEmployeeById(Long airlineId, Long aircraftId,
			Long employeeId) {
		Employee employee = employeeDao.findById(employeeId)
				.orElseThrow(() -> new NoSuchElementException
						("Employee not found with ID: " + employeeId));
		
	    boolean isEmployeeInAirline = employee.getAirlines().stream()
	            .anyMatch(airline -> airline.getAirlineId().equals(airlineId));

	    boolean isEmployeeInAircraft = employee.getAircraft() != null && 
	            employee.getAircraft().getAircraftId().equals(aircraftId);

	    if (isEmployeeInAirline && isEmployeeInAircraft) {
	        return employee;
	    } else {
	        throw new IllegalArgumentException
	        ("This employee does not work for this airline with ID " + airlineId);
	    }
	}

	@Transactional
	public Aircraft getAircraftById(Long aircraftId) {
		return aircraftDao.findById(aircraftId)
				.orElseThrow(() -> new NoSuchElementException
						("Aircraft not found with ID: " + aircraftId));
	}
	
	@Transactional
	public Employee getEmployeeById(Long employeeId) {
		return employeeDao.findById(employeeId)
				.orElseThrow(() -> new NoSuchElementException
						("Employee not found with ID: " + employeeId));
	}

	@Transactional 
	public ResponseEntity<String> deleteAircraftById(Long aircraftId) {
		Aircraft aircraft = aircraftDao.findById(aircraftId)
				.orElseThrow(() -> new NoSuchElementException
						("Aircraft not found with ID: " + aircraftId));
		aircraftDao.delete(aircraft);
		return ResponseEntity.ok().body("aircraft with ID " + aircraftId + " deleted successfully");
	}

	@Transactional
	public ResponseEntity<String> deleteEmployeeById(Long employeeId) {
		Employee employee = employeeDao.findById(employeeId)
				.orElseThrow(() -> new NoSuchElementException
						("Employee not found with ID: " + employeeId));
		employeeDao.delete(employee);
		return ResponseEntity.ok().body("employee with ID " + employeeId + " deleted successfully");
	}
	
	@Transactional
    public List<AircraftData> retrieveAllAircraft() {
        List<Aircraft> aircraft = aircraftDao.findAll();
        return aircraft.stream()
                .map(AircraftData::new)
                .collect(Collectors.toList());
    }

	@Transactional
	public List<EmployeeData> retrieveAllEmployees() {
		List<Employee> employee = employeeDao.findAll();
		return employee.stream()
				.map(EmployeeData::new)
				.collect(Collectors.toList());
	}
	
	@Transactional
	public AircraftData retrieveAircraftById(Long aircraftId) {
		Aircraft aircraft = aircraftDao.findById(aircraftId)
	            .orElseThrow(() -> new NoSuchElementException
	            		("Aircraft not found with ID: " + aircraftId));
	    return new AircraftData(aircraft);
	}

	@Transactional
	public EmployeeData retrieveEmployeeById(Long employeeId) {
		Employee employee = employeeDao.findById(employeeId)
				.orElseThrow(() -> new NoSuchElementException
						("Employee not found with ID: " + employeeId));
		return new EmployeeData(employee);
	}
	
	@Transactional
	public AircraftData updateAircraft(Long aircraftId, AircraftData updatedAircraft) {
	    Aircraft aircraft = aircraftDao.findById(aircraftId)
	            .orElseThrow(() -> new NoSuchElementException
	            		("Aircraft not found with ID: " + aircraftId));
	
	    aircraft.setManufacturer(updatedAircraft.getManufacturer());
	    aircraft.setModel(updatedAircraft.getModel());
	    aircraft.setTailNumber(updatedAircraft.getTailNumber());
	    aircraft.setNumberOfEngines(updatedAircraft.getNumberOfEngines());
	    aircraft.setPropulsionType(updatedAircraft.getPropulsionType());
	
	    aircraft = aircraftDao.save(aircraft);
	
	    return new AircraftData(aircraft);
	}
	
	@Transactional
	public EmployeeData updateEmployee(Long employeeId, EmployeeData updateEmployee) {
		Employee employee = employeeDao.findById(employeeId)
				.orElseThrow(() -> new NoSuchElementException
						("Employee not found with ID: " + employeeId));
		
		employee.setFirstName(updateEmployee.getFirstName());
		employee.setLastName(updateEmployee.getLastName());
		employee.setEmail(updateEmployee.getEmail());
		employee.setJobTitle(updateEmployee.getJobTitle());
		
		employee = employeeDao.save(employee);
		
		return new EmployeeData(employee);
	}

}