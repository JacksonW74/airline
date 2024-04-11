package airline.entity;

import java.util.Set;

import airline.controller.model.AirlineData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Data
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long airlineId;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Aircraft> aircraft;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Customer> customers;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee> employees;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Station> stations;
    
    public Airline() {}
    
    public Airline(AirlineData airlineData) {
    	this.name = airlineData.getName();
    	this.address = airlineData.getAddress();
    	this.city = airlineData.getCity();
    	this.state = airlineData.getState();
    	this.zip = airlineData.getZip();
    	this.phone = airlineData.getPhone();
    }
}
