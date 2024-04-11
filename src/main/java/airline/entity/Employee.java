package airline.entity;

import java.util.HashSet;
import java.util.Set;

import airline.controller.model.EmployeeData;
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
import lombok.ToString;

@Entity
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private String dateOfHire;
    
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline; // Many-to-One relationship with Airline

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "station_id") // This will create a foreign key column in the employee table for the associated station.
    private Station station;
    
//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @ManyToMany
//    @JoinTable(
//        name = "Crew_Routes", // Join table name
//        joinColumns = @JoinColumn(name = "employee_id"), // FK column in join table referencing Employee
//        inverseJoinColumns = @JoinColumn(name = "route_id") // FK column in join table referencing Route
//    )
    
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "employee", cascade = CascadeType.PERSIST)
    private Set<Route> route = new HashSet<>();
    
//    private Set<Route> routes; // Many-to-Many relationship with Route
    
    public Employee() {}
    
    public Employee(EmployeeData employeeData) {
    	this.firstName = employeeData.getFirstName();
    	this.lastName = employeeData.getLastName();
    	this.email = employeeData.getEmail();
    	this.jobTitle = employeeData.getJobTitle();
    	this.dateOfHire = employeeData.getDateOfHire();
    }
}
