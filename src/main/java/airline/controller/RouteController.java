package airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import airline.controller.model.RouteData;
import airline.service.RouteService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/routes")
@Slf4j
@Data
public class RouteController {

    private RouteService routeService;
    
    @Autowired
    public RouteController(RouteService routeService) {
    	this.routeService = routeService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RouteData createRoute(@RequestBody RouteData routeData) {
    	log.info("Creating a Route {}", routeData);
        return routeService.createRoute(routeData);
    }

    @GetMapping("/{id}")
    public RouteData getRoute(@PathVariable Long id) {
    	log.info("Retrieving Route with ID={}", id);
        return routeService.getRoute(id);
    }

    @GetMapping
    public List<RouteData> getAllRoutes() {
    	log.info("Retrieving all Route");
        return routeService.getAllRoutes();
    }

    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateRoute(@PathVariable Long id, 
    		@RequestBody RouteData routeData) {
    	log.info("Updating Route {}", routeData);
    	routeService.updateRoute(id, routeData);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteRouteById(@PathVariable Long id) {
    	log.info("Deleting Route with ID={}", id);
        routeService.deleteRouteById(id);
    }
}
