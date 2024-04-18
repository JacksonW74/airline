package airline.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
public class Aircraft {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aircraft_id")
    private Long aircraftId;

    private String manufacturer;
    private String model;
    private String tailNumber;
    private int numberOfEngines;
    private String propulsionType;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;

//    @EqualsAndHashCode.Exclude
//    @OneToMany(mappedBy = "aircraft")
//    private Set<Employee> employees = new HashSet<>();
}
