package airline.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Route {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long routeId;
	private String arrivalAirport;
	private String departureAirport;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany
	@JoinTable(name = "Route_Aircraft", joinColumns = @JoinColumn(name = "routeId"), inverseJoinColumns = @JoinColumn(name = "aircraftId"))
	private Set<Aircraft> aircraft;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToMany
	@JoinTable(name = "Route_Customer", joinColumns = @JoinColumn(name = "routeId"), inverseJoinColumns = @JoinColumn(name = "customerId"))
	private Set<Customer> customer;

//    @EqualsAndHashCode.Exclude
//    @ToString.Exclude
//    @ManyToMany(cascade = CascadeType.PERSIST)
//    private Set<Employee> employee = new HashSet<>();

	@EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany
    @JoinTable(
    		name = "Crew_Routes", // Join table name
    		joinColumns = @JoinColumn(name = "employee_id"), // FK column in join table referencing Employee
    		inverseJoinColumns = @JoinColumn(name = "route_id")) // FK column in join table referencing Route
	
	private Set<Employee> employee = new HashSet<>();
	
//    public Route() {}

//    public Route(RouteData routeData) {
//    	this.routeId = routeData.getRouteId();
//    	this.arrivalAirport = routeData.getArrivalAirport();
//    	this.departureAirport = routeData.getDepartureAirport();
//    }
}
