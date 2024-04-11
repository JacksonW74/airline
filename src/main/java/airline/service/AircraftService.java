package airline.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import airline.controller.model.AircraftData;
import airline.dao.AircraftDao;
import airline.entity.Aircraft;
import airline.entity.Airline;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor  
@Data
public class AircraftService {

	@Autowired
    private AircraftDao aircraftDao;

    public AircraftService(AircraftDao aircraftDao) {
        this.aircraftDao = aircraftDao;
    }
    //	first option to save and create aircraft
//    @Transactional(readOnly = false)
//    public AircraftData saveAircraft(AircraftData aircraftData) {
//    	Aircraft aircraft = aircraftData.toAircraft();
//    	Aircraft dbAircraft = aircraftDao.save(aircraft);
//    	
//    	return new AircraftData(dbAircraft);
//    }
//    
//    @Transactional(readOnly = false)
//    public AircraftData createAircraft(AircraftData aircraftData) {
//        Aircraft aircraft = aircraftData.toAircraft();
//        aircraft = aircraftDao.save(aircraft);
//        return new AircraftData(aircraft);
//    }

    //	second option to save and create aircraft
    @Transactional(readOnly = false)
    public AircraftData saveAircraft(AircraftData aircraftData, Airline airline) {
        Aircraft aircraft = aircraftData.toAircraft(airline);
        Aircraft dbAircraft = aircraftDao.save(aircraft);
        return new AircraftData(dbAircraft);
    }

    @Transactional(readOnly = false)
    public AircraftData createAircraft(AircraftData aircraftData, Airline airline) {
        Aircraft aircraft = aircraftData.toAircraft(airline);
        aircraft = aircraftDao.save(aircraft);
        return new AircraftData(aircraft);
    }

    
    @Transactional(readOnly = true)
    public AircraftData getAircraft(Long id) {
        Aircraft aircraft = findAircraftById(id);
        return new AircraftData(aircraft);
    }

    @Transactional(readOnly = true)
    public List<AircraftData> getAllAircrafts() {
        List<Aircraft> aircrafts = aircraftDao.findAll();
        return aircrafts.stream().map(AircraftData::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    public void updateAircraft(Long id, AircraftData aircraftData) {
        Aircraft aircraft = aircraftDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                		"Aircraft with ID=" + id + " was not found"));

        aircraft.setManufacturer(aircraftData.getManufacturer());
        aircraft.setModel(aircraftData.getModel());
        aircraft.setTailNumber(aircraftData.getTailNumber());
        aircraft.setNumberOfEngines(aircraftData.getNumberOfEngines());
        aircraft.setPropulsionType(aircraftData.getPropulsionType());

        aircraftDao.save(aircraft);
    }

	@Transactional
    public void deleteAircraft(Long id) {
        Aircraft aircraft = findAircraftById(id);
        aircraftDao.delete(aircraft);
    }

    private Aircraft findAircraftById(Long id) {
        return aircraftDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                		"Aircraft with ID=" + id + " was not found"));
    }

    @Transactional(readOnly = false)
    public void deleteAircraftById(Long id) {
        Aircraft aircraft = findAircraftById(id);
        aircraftDao.delete(aircraft);
    }
}
