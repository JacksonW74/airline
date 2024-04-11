package airline.controller.model;

import airline.entity.Aircraft;
import airline.entity.Airline;
import jakarta.persistence.Entity;
import lombok.Data;

@Data 
//@Entity
public class AircraftData {
    private Long aircraftId;
    private String manufacturer;
    private String model;
    private String tailNumber;
    private Integer numberOfEngines;
    private String propulsionType;
	private Long airlineId;

    public AircraftData() {}

    public AircraftData(Long airlineId, Long aircraftId, 
    		String manufacturer, String model, String tailNumber, Integer numberOfEngines, 
    		String propulsionType) {
    	this.airlineId = airlineId;
        this.aircraftId = aircraftId;
        this.manufacturer = manufacturer;
        this.model = model;
        this.tailNumber = tailNumber;
        this.numberOfEngines = numberOfEngines;
        this.propulsionType = propulsionType;
    }
    
    public AircraftData(Aircraft aircraft) {
        this.aircraftId = aircraft.getAircraftId();
        this.manufacturer = aircraft.getManufacturer();
        this.model = aircraft.getModel();
        this.tailNumber = aircraft.getTailNumber();
        this.numberOfEngines = aircraft.getNumberOfEngines();
        this.propulsionType = aircraft.getPropulsionType();
    }
    //original method toAircraft
//    public Aircraft toAircraft() {
//        Aircraft aircraft = new Aircraft();
//        aircraft.setAircraftId(aircraftId);
//        aircraft.setManufacturer(manufacturer);
//        aircraft.setModel(model);
//        aircraft.setTailNumber(tailNumber);
//        aircraft.setNumberOfEngines(numberOfEngines);
//        aircraft.setPropulsionType(propulsionType);
//        return aircraft;
//    }
    //	second option toAircraft
//    public Aircraft toAircraft() {
//        Aircraft aircraft = new Aircraft();
//        aircraft.setManufacturer(manufacturer);
//        aircraft.setModel(model);
//        aircraft.setTailNumber(tailNumber);
//        aircraft.setNumberOfEngines(numberOfEngines);
//        aircraft.setPropulsionType(propulsionType);
//
//        // Create Airline entity and set the ID
//        if (airlineId != null) {
//            Airline airline = new Airline();
//            airline.setId(airlineId);
//            // Set the Airline entity in Aircraft
//            aircraft.setAirline(airline);
//        }
//
//        return aircraft;
//    }
    // third option toAircraft
    public Aircraft toAircraft(Airline airline) {
        Aircraft aircraft = new Aircraft();
        aircraft.setManufacturer(manufacturer);
        aircraft.setModel(model);
        aircraft.setTailNumber(tailNumber);
       	aircraft.setNumberOfEngines(numberOfEngines);
       	aircraft.setPropulsionType(propulsionType);
        aircraft.setAirline(airline);
        return aircraft;
    }    
    
//    public void setAirlineId(Airline airlineId) {
//        this.airlineId = airlineId;
//    }

    public void setAircraftId(Long aircraftId) {
        this.aircraftId = aircraftId;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setTailNumber(String tailNumber) {
        this.tailNumber = tailNumber;
    }

    public void setNumberOfEngines(Integer numberOfEngines) {
        this.numberOfEngines = numberOfEngines;
    }

    public void setPropulsionType(String propulsionType) {
        this.propulsionType = propulsionType;
    }

}
