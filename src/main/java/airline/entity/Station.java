package airline.entity;

import java.util.HashSet;
import java.util.Set;

import airline.controller.model.StationData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stationId;

    private String name;
    private String city;
    private String state;
    private Integer zip;
    private String phone;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "airline_id") 
    private Airline airline;

    @EqualsAndHashCode.Exclude
    @OneToMany(cascade = CascadeType.PERSIST) 
    private Set<Employee> employees = new HashSet<>();
    
    public Station() {}

    public Station(StationData stationData) {
        this.name = stationData.getName();
        this.city = stationData.getCity();
        this.state = stationData.getState();
        this.zip = stationData.getZip();
        this.phone = stationData.getPhone();
    }
}
