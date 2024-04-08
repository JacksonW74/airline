package airline.entity;

import java.util.Set;

import airline.controller.model.EmployeeData;
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
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private String dateOfHire;
    
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline; // Many-to-One relationship with Airline

    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "station_id") // This will create a foreign key column in the employee table for the associated station.
    private Station station;
    
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
        name = "Crew_Routes", // Join table name
        joinColumns = @JoinColumn(name = "employee_id"), // FK column in join table referencing Employee
        inverseJoinColumns = @JoinColumn(name = "route_id") // FK column in join table referencing Route
    )
    
    private Set<Route> routes; // Many-to-Many relationship with Route
    
    public Employee(EmployeeData employeeData) {
    	this.firstName = employeeData.getFirstName();
    	this.lastName = employeeData.getLastName();
    	this.email = employeeData.getEmail();
    	this.jobTitle = employeeData.getJobTitle();
    	this.dateOfHire = employeeData.getDateOfHire();
    }
}
