package airline.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.jdbc.JdbcTestUtils;

import airline.controller.model.AirlineData;
import airline.controller.model.EmployeeData;
import airline.controller.model.RouteData;
import airline.controller.model.StationData;

@Component
public class AirlineServiceTestSupport {

    private static final String AIRLINE_TABLE = "airline";
    private static final String EMPLOYEE_TABLE = "employee";
    private static final String ROUTE_TABLE = "route";
    private static final String STATION_TABLE = "station";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    protected AirlineData buildAirlineData() {
        // Implement logic to build AirlineData object for testing
    }

    protected int rowsInAirlineTable() {
        return JdbcTestUtils.countRowsInTable(jdbcTemplate, AIRLINE_TABLE);
    }

    protected void insertAirline(AirlineData airlineData) {
        // Implement logic to insert airline into database
    }

    protected AirlineData retrieveAirline(Long airlineId) {
        // Implement logic to retrieve airline from database
    }

    protected List<AirlineData> retrieveAllAirlines() {
        // Implement logic to retrieve all airlines from database
    }

    protected AirlineData updateAirline(AirlineData airlineData) {
        // Implement logic to update airline in database
    }

    protected void deleteAirline(Long airlineId) {
        // Implement logic to delete airline from database
    }

    // Similar methods for Employee, Route, and Station entities

    protected int rowsInEmployeeTable() {
        return JdbcTestUtils.countRowsInTable(jdbcTemplate, EMPLOYEE_TABLE);
    }

    protected int rowsInRouteTable() {
        return JdbcTestUtils.countRowsInTable(jdbcTemplate, ROUTE_TABLE);
    }

    protected int rowsInStationTable() {
        return JdbcTestUtils.countRowsInTable(jdbcTemplate, STATION_TABLE);
    }

    protected void assertAirlineAndEmployeeRowsAreAddedCorrectly() {
        assertThat(rowsInAirlineTable()).isOne();
        assertThat(rowsInEmployeeTable()).isEqualTo(2); // Adjust this as per your test data
        // Add assertions for other tables as needed
    }

    protected void assertAirlineAndEmployeeRowsAreGoneAfterDeletion() {
        assertThat(rowsInAirlineTable()).isZero();
        assertThat(rowsInEmployeeTable()).isZero();
        // Add assertions for other tables as needed
    }
}
