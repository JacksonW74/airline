package airline.dao;

import airline.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationDao extends JpaRepository<Station, Long> {
    // Additional custom methods for station-related database operations can be defined here
}
