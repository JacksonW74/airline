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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long employeeId;

    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "aircraft_id")
    private Aircraft aircraft;

    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
            name = "employee_airline",
            joinColumns = @JoinColumn(name = "employee_id"),
            inverseJoinColumns = @JoinColumn(name = "airline_id")
    )
    private Set<Airline> airlines = new HashSet<>();
}