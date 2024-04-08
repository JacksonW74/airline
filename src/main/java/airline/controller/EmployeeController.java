package airline.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import airline.controller.model.EmployeeData;
import airline.service.EmployeeService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/employees")
@Slf4j
@Data
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public EmployeeData createEmployee(@RequestBody EmployeeData employeeData) {
        log.info("Creating an Employee {}", employeeData);
        return employeeService.createEmployee(employeeData);
    }

    @GetMapping("/{id}")
    public EmployeeData getEmployeeById(@PathVariable Long id) {
        log.info("Retrieving Employee with ID={}", id);
        return employeeService.getEmployee(id);
    }
    
    @GetMapping
    public List<EmployeeData> getAllEmployees() {
    	log.info("Retrieving all Employee");
        return employeeService.getAllEmployees();
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateEmployee(@PathVariable Long id, 
    		@RequestBody EmployeeData employeeData) {
    	log.info("Updating Employee {}", employeeData);
    	employeeService.updateEmployee(id, employeeData);
    }

    @DeleteMapping("/{Id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteEmployeeById(@PathVariable Long id) {
        log.info("Deleting Employee with ID={}", id);
        employeeService.deleteEmployeeById(id);
    }
}
