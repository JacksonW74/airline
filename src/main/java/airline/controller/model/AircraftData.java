package airline.controller.model;

import airline.entity.Aircraft;
import lombok.Data;

@Data
public class AircraftData {
    private Long airlineId;
    private Long aircraftId;
    private String manufacturer;
    private String model;
    private String tailNumber;
    private Integer numberOfEngines;
    private String propulsionType;

    public AircraftData() {}

    public AircraftData(Long airlineId, String airlineName, Long aircraftId, 
    		String manufacturer, String model, String tailNumber, Integer numberOfEngines, 
    		String propulsionType, Integer numberOfPassengers) {
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
    
    public Aircraft toAircraft() {
        Aircraft aircraft = new Aircraft();
        aircraft.setAircraftId(this.aircraftId);
        aircraft.setManufacturer(this.manufacturer);
        aircraft.setModel(this.model);
        aircraft.setTailNumber(this.tailNumber);
        aircraft.setNumberOfEngines(this.numberOfEngines);
        aircraft.setPropulsionType(this.propulsionType);
        return aircraft;
    }
    
    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

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
