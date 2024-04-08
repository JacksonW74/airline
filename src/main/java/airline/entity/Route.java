package airline.entity;

import java.util.HashSet;
import java.util.Set;

import airline.controller.model.RouteData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long routeId;
    private String arrivalAirport;
    private String departureAirport;

    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
            name = "Route_Aircraft",
            joinColumns = @JoinColumn(name = "routeId"),
            inverseJoinColumns = @JoinColumn(name = "aircraftId")
    )
    private Set<Aircraft> aircraft;
    
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(
        name = "Route_Customer",
        joinColumns = @JoinColumn(name = "routeId"),
        inverseJoinColumns = @JoinColumn(name = "customerId")
    )
    private Set<Customer> customer; 
    
    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Employee> employee = new HashSet<>();
    
    public Route(RouteData routeData) {
    	this.routeId = routeData.getRouteId();
    	this.arrivalAirport = routeData.getArrivalAirport();
    	this.departureAirport = routeData.getDepartureAirport();
    }
}
