package airline.dao;

import airline.entity.Aircraft;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AircraftDao extends JpaRepository<Aircraft, Long> {
}
