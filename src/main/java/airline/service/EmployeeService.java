package airline.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import airline.controller.model.EmployeeData;
import airline.dao.EmployeeDao;
import airline.entity.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor  
@Data
public class EmployeeService {

	@Autowired
    private EmployeeDao employeeDao;

    public EmployeeService(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Transactional(readOnly = false)
    public EmployeeData saveEmployee(EmployeeData employeeData) {
    	Employee employee = employeeData.toEmployee();
    	Employee dbEmployee = employeeDao.save(employee);
    	
    	return new EmployeeData(dbEmployee);
    }
    @Transactional
    public EmployeeData createEmployee(EmployeeData employeeData) {
        Employee employee = employeeData.toEmployee();
        employee = employeeDao.save(employee);
        return new EmployeeData(employee);
    }

    @Transactional(readOnly = true)
    public EmployeeData getEmployee(Long id) {
        Employee employee = findEmployeeById(id);
        return new EmployeeData(employee);
    }

    @Transactional(readOnly = true)
    public List<EmployeeData> getAllEmployees() {
        List<Employee> employees = employeeDao.findAll();
        return employees.stream().map(EmployeeData::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    public void updateEmployee(Long id, EmployeeData employeeData) {
        Employee employee = employeeDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                		"Employee with ID=" + id + " was not found"));

        employee.setFirstName(employeeData.getFirstName());
        employee.setLastName(employeeData.getLastName());
        employee.setEmail(employeeData.getEmail());
        employee.setJobTitle(employeeData.getJobTitle());
        employee.setDateOfHire(employeeData.getDateOfHire());

        employeeDao.save(employee);
    }

	@Transactional
    public void deleteEmployee(Long id) {
        Employee employee = findEmployeeById(id);
        employeeDao.delete(employee);
    }

    private Employee findEmployeeById(Long id) {
        return employeeDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                		"Employee with ID=" + id + " was not found"));
    }

    @Transactional(readOnly = false)
    public void deleteEmployeeById(Long id) {
        Employee employee = findEmployeeById(id);
        employeeDao.delete(employee);
    }
}
