package airline.entity;

import java.util.HashSet;
import java.util.Set;

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
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline; 

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @ManyToMany(mappedBy = "customer", cascade = CascadeType.PERSIST)
    private Set<Route> routes = new HashSet<>();   
    
//    public Customer() {}
//    
//    public Customer(CustomerData customerData) {
//        this.firstName = customerData.getFirstName();
//        this.lastName = customerData.getLastName();
//        this.email = customerData.getEmail();
//        this.phone = customerData.getPhone();
//    }
}
