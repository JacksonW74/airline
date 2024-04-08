package airline.entity;

import java.util.HashSet;
import java.util.Set;

import airline.controller.model.AircraftData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long aircraftId;
    private String manufacturer;
    private String model;
    private String tailNumber;
    private int numberOfEngines;
    private String propulsionType;

    // Relationships
    @ManyToOne
    @JoinColumn(name = "airline_id")
    @EqualsAndHashCode.Exclude
    private Airline airline;
    
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Route> route = new HashSet<>();
    
    public Aircraft() {}
    
    public Aircraft(AircraftData aircraftData) {
        this.manufacturer = aircraftData.getManufacturer();
        this.model = aircraftData.getModel();
        this.tailNumber = aircraftData.getTailNumber();
        this.numberOfEngines = aircraftData.getNumberOfEngines();
        this.propulsionType = aircraftData.getPropulsionType();
    }
}