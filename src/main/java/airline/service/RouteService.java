package airline.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import airline.controller.model.RouteData;
import airline.dao.RouteDao;
import airline.entity.Route;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor  
@Data
public class RouteService {

	@Autowired
    private RouteDao routeDao;

    public RouteService(RouteDao routeDao) {
        this.routeDao = routeDao;
    }

    @Transactional(readOnly = false)
    public RouteData saveRoute(RouteData routeData) {
    	Route route = routeData.toRoute();
    	Route dbRoute = routeDao.save(route);
    	
    	return new RouteData(dbRoute);
    }
    
    @Transactional
    public RouteData createRoute(RouteData routeData) {
        Route route = routeData.toRoute();
        route = routeDao.save(route);
        return new RouteData(route);
    }

    @Transactional(readOnly = true)
    public RouteData getRoute(Long id) {
        Route route = findRouteById(id);
        return new RouteData(route);
    }

    @Transactional(readOnly = true)
    public List<RouteData> getAllRoutes() {
        List<Route> routes = routeDao.findAll();
        return routes.stream().map(RouteData::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    public void updateRoute(Long id, RouteData routeData) {
        Route route = routeDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                		"Route with ID=" + id + " was not found"));

        route.setArrivalAirport(routeData.getArrivalAirport());
        route.setDepartureAirport(routeData.getDepartureAirport());
        
        routeDao.save(route);
    }

	@Transactional
    public void deleteRoute(Long id) {
        Route route = findRouteById(id);
        routeDao.delete(route);
    }

    private Route findRouteById(Long id) {
        return routeDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                		"Route with ID=" + id + " was not found"));
    }

    @Transactional(readOnly = false)
    public void deleteRouteById(Long id) {
        Route route = findRouteById(id);
        routeDao.delete(route);
    }
}
