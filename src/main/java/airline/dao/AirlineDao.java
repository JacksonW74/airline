package airline.dao;

import airline.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirlineDao extends JpaRepository<Airline, Long> {
    // Additional custom methods for airline-related database operations can be defined here
}
