package airline.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import airline.controller.model.AirlineData;
import airline.dao.AirlineDao;
import airline.entity.Airline;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor  
@Data
public class AirlineService {

	@Autowired
    private AirlineDao airlineDao;

    public AirlineService(AirlineDao airlineDao) {
        this.airlineDao = airlineDao;
    }
    
    @Transactional(readOnly = false)
    public AirlineData saveAirline(AirlineData airlineData) {
    	Airline airline = airlineData.toAirline();
    	Airline dbAirline = airlineDao.save(airline);
    	
    	return new AirlineData(dbAirline);
    }

    @Transactional
    public AirlineData createAirline(AirlineData airlineData) {
        Airline airline = airlineData.toAirline();
        airline = airlineDao.save(airline);
        return new AirlineData(airline);
    }

    @Transactional(readOnly = true)
    public AirlineData getAirline(Long id) {
        Airline airline = findAirlineById(id);
        return new AirlineData(airline);
    }

    @Transactional(readOnly = true)
    public List<AirlineData> getAllAirlines() {
        List<Airline> airlines = airlineDao.findAll();
        return airlines.stream().map(AirlineData::new).collect(Collectors.toList());
    }

    @Transactional
    public void updateAirline(Long id, AirlineData airlineData) {
        Airline airline = airlineDao.findById(id)
        		.orElseThrow(() -> new NoSuchElementException(
        				"Airline with ID=" + id + " was not found"));
        
        airline.setName(airlineData.getName());
        airline.setAddress(airlineData.getAddress());
        airline.setCity(airlineData.getCity());
        airline.setState(airlineData.getState());
        airline.setZip(airlineData.getZip());
        airline.setPhone(airlineData.getPhone());
        
        airlineDao.save(airline);
    }

    @Transactional
    public void deleteAirline(Long id) {
        Airline airline = findAirlineById(id);
        airlineDao.delete(airline);
    }
    
    public Airline findAirlineById(Long id) {
        return airlineDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Airline with ID=" + id + " was not found"));
    }
    
    @Transactional(readOnly = false)
    public void deleteAirlineById(Long id) {
    	Airline airline = findAirlineById(id);
    	airlineDao.delete(airline);
    }
}
