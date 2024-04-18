package airline.controller.model;

import airline.entity.Aircraft;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
public class AircraftData {
    private Long aircraftId;
    private String manufacturer;
    private String model;
    private String tailNumber;
    private Integer numberOfEngines;
    private String propulsionType;
    
    public AircraftData(Aircraft aircraft) {
        this.aircraftId = aircraft.getAircraftId();
        this.manufacturer = aircraft.getManufacturer();
        this.model = aircraft.getModel();
        this.tailNumber = aircraft.getTailNumber();
        this.numberOfEngines = aircraft.getNumberOfEngines();
        this.propulsionType = aircraft.getPropulsionType();
    }
}
