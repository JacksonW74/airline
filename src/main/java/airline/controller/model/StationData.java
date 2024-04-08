package airline.controller.model;

import airline.entity.Station;
import lombok.Data;

@Data
public class StationData {
    private Long airlineId;
    private Long stationId;
    private String name;
    private String city;
    private String state;
    private Integer zip;
    private String phone;

    public StationData() {}

    public StationData(Long airlineId, Long stationId, 
    		String name, String city, String state, 
    		Integer zip, String phone) {
        this.airlineId = airlineId;
    	this.stationId = stationId;
        this.name = name;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
    }
    
    public StationData(Station station) {
        this.stationId = station.getStationId();
        this.name = station.getName();
        this.city = station.getCity();
        this.state = station.getState();
        this.zip = station.getZip();
        this.phone = station.getPhone();
    }
    
    public Station toStation() {
        Station station = new Station();
        station.setStationId(this.stationId);
        station.setName(this.name);
        station.setCity(this.city);
        station.setState(this.state);
        station.setZip(this.zip);
        station.setPhone(this.phone);
        return station;
    }
    
    public void setAirlineId(Long airlineId) {
        this.airlineId = airlineId;
    }

    public void setStationId(Long stationId) {
        this.stationId = stationId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
