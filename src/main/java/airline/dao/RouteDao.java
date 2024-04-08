package airline.dao;

import airline.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteDao extends JpaRepository<Route, Long> {
    // Additional custom methods for route-related database operations can be defined here
}
