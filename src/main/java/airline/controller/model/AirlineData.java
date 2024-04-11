package airline.controller.model;

import airline.entity.Airline;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AirlineData {

    private Long airlineId;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String phone;

    public AirlineData(Airline airline) {
        this.airlineId = airline.getAirlineId();
        this.name = airline.getName();
        this.address = airline.getAddress();
        this.city = airline.getCity();
        this.state = airline.getState();
        this.zip = airline.getZip();
        this.phone = airline.getPhone();
    }

    public Airline toAirline() {
        Airline airline = new Airline();
        airline.setAirlineId(this.airlineId);
        airline.setName(this.name);
        airline.setAddress(this.address);
        airline.setCity(this.city);
        airline.setState(this.state);
        airline.setZip(this.zip);
        airline.setPhone(this.phone);
        return airline;
    }
}
