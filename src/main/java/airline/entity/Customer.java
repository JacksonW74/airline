package airline.entity;

import java.util.HashSet;
import java.util.Set;

import airline.controller.model.CustomerData;
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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    
    @EqualsAndHashCode.Exclude
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline; // Many-to-One relationship with Airline

    @EqualsAndHashCode.Exclude
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Route> routes = new HashSet<>(); // Many-to-Many relationship with Route   
    
    public Customer(CustomerData customerData) {
        this.firstName = customerData.getFirstName();
        this.lastName = customerData.getLastName();
        this.email = customerData.getEmail();
        this.phone = customerData.getPhone();
    }
}
