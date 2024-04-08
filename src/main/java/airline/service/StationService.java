package airline.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import airline.controller.model.StationData;
import airline.dao.StationDao;
import airline.entity.Station;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor  
@Data
public class StationService {

	@Autowired
    private StationDao stationDao;

    public StationService(StationDao stationDao) {
        this.stationDao = stationDao;
    }

    @Transactional(readOnly = false)
    public StationData saveStation(StationData stationData) {
    	Station station = stationData.toStation();
    	Station dbStation = stationDao.save(station);
    	
    	return new StationData(dbStation);
    }
    
    @Transactional
    public StationData createStation(StationData stationData) {
        Station station = stationData.toStation();
        station = stationDao.save(station);
        return new StationData(station);
    }

    @Transactional(readOnly = true)
    public StationData getStation(Long id) {
        Station station = findStationById(id);
        return new StationData(station);
    }

    @Transactional(readOnly = true)
    public List<StationData> getAllStations() {
        List<Station> stations = stationDao.findAll();
        return stations.stream().map(StationData::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    public void updateStation(Long id, StationData stationData) {
        Station station = stationDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                		"Station with ID=" + id + " was not found"));

        station.setName(stationData.getName());
        station.setCity(stationData.getCity());
        station.setState(stationData.getState());
        station.setZip(stationData.getZip());
        station.setPhone(stationData.getPhone());

        stationDao.save(station);
    }

	@Transactional
    public void deleteStation(Long id) {
        Station station = findStationById(id);
        stationDao.delete(station);
    }

    private Station findStationById(Long id) {
        return stationDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                		"Station with ID=" + id + " was not found"));
    }

    @Transactional(readOnly = false)
    public void deleteStationById(Long id) {
        Station station = findStationById(id);
        stationDao.delete(station);
    }
}
