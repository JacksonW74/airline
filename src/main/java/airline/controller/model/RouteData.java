package airline.controller.model;

import airline.entity.Route;
import lombok.Data;

@Data
public class RouteData {
    private Long routeId;
    private String departureAirport;
    private String arrivalAirport;
    
    public RouteData() {}
    
    public RouteData(Long routeId, String departureAirport, 
    		String arrivalAirport) {
        this.routeId = routeId;
        this.departureAirport = departureAirport;
        this.arrivalAirport = arrivalAirport;
    }
    
    public RouteData(Route route) {
    	this.routeId = route.getRouteId();
    	this.arrivalAirport = route.getArrivalAirport();
    	this.departureAirport = route.getDepartureAirport();
    }
	
    public Route toRoute() {
    	Route route = new Route();
        route.setRouteId(this.routeId);
        route.setArrivalAirport(this.arrivalAirport);
        route.setDepartureAirport(this.departureAirport);
		return route;
	}
    
    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }
}
