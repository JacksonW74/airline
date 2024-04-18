package airline.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Airline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "airline_id")
    private Long airlineId;

    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;

    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "airline", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Aircraft> aircraft = new HashSet<>();

    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "airlines", cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<>();
}