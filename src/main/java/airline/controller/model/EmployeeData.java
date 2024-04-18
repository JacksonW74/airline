package airline.controller.model;

import airline.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeData {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private Long airlineId;
    private Long aircraftId; 

    public EmployeeData(Employee employee) {
        this.employeeId = employee.getEmployeeId();
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.email = employee.getEmail();
        this.jobTitle = employee.getJobTitle();
        if (employee.getAircraft() != null) {
            this.aircraftId = employee.getAircraft().getAircraftId();
        }
    }
}
