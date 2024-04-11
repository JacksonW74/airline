//package airline.controller;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Component;
//import org.springframework.test.jdbc.JdbcTestUtils;
//
//import airline.controller.model.AirlineData;
//import airline.controller.model.EmployeeData;
//import airline.controller.model.RouteData;
//import airline.controller.model.StationData;
//
//@Component
//public class AirlineServiceTestSupport {
//
//    private static final String AIRLINE_TABLE = "airline";
//    private static final String EMPLOYEE_TABLE = "employee";
//    private static final String ROUTE_TABLE = "route";
//    private static final String STATION_TABLE = "station";
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    protected AirlineData buildAirlineData() {
//        }
//
//    protected int rowsInAirlineTable() {
//        return JdbcTestUtils.countRowsInTable(jdbcTemplate, AIRLINE_TABLE);
//    }
//
//    protected void insertAirline(AirlineData airlineData) {
//        }
//
//    protected AirlineData retrieveAirline(Long airlineId) {
//        }
//
//    protected List<AirlineData> retrieveAllAirlines() {
//        }
//
//    protected AirlineData updateAirline(AirlineData airlineData) {
//        }
//
//    protected void deleteAirline(Long airlineId) {
//        }
//
//    protected int rowsInEmployeeTable() {
//        return JdbcTestUtils.countRowsInTable(jdbcTemplate, EMPLOYEE_TABLE);
//    }
//
//    protected int rowsInRouteTable() {
//        return JdbcTestUtils.countRowsInTable(jdbcTemplate, ROUTE_TABLE);
//    }
//
//    protected int rowsInStationTable() {
//        return JdbcTestUtils.countRowsInTable(jdbcTemplate, STATION_TABLE);
//    }
//
//    protected void assertAirlineAndEmployeeRowsAreAddedCorrectly() {
//        assertThat(rowsInAirlineTable()).isOne();
//        assertThat(rowsInEmployeeTable()).isEqualTo(2); 
//    }
//
//    protected void assertAirlineAndEmployeeRowsAreGoneAfterDeletion() {
//        assertThat(rowsInAirlineTable()).isZero();
//        assertThat(rowsInEmployeeTable()).isZero();
//    }
//}
