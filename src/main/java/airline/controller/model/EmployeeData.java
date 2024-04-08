package airline.controller.model;

import airline.entity.Employee;
import lombok.Data;

@Data
public class EmployeeData {
    private Long employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String jobTitle;
    private String dateOfHire;

    public EmployeeData() {}

    public EmployeeData(Long employeeId, String firstName, 
    	    String lastName, String email, 
    	    String jobTitle, String dateOfHire) {
    	    this.employeeId = employeeId;
    	    this.firstName = firstName;
    	    this.lastName = lastName;
    	    this.email = email;
    	    this.jobTitle = jobTitle;
    	    this.dateOfHire = dateOfHire;
    }
    
    public EmployeeData(Employee employee) {
    	this.employeeId = employee.getEmployeeId();
    	this.firstName = employee.getFirstName();
    	this.lastName = employee.getLastName();
    	this.email = employee.getEmail();
    	this.jobTitle = employee.getJobTitle();
    	this.dateOfHire = employee.getDateOfHire();
    }
    
    public Employee toEmployee() {
    	Employee employee = new Employee();
    	employee.setEmployeeId(this.employeeId);
    	employee.setFirstName(this.firstName);
    	employee.setLastName(this.lastName);
    	employee.setEmail(this.email);
    	employee.setJobTitle(this.jobTitle);
    	employee.setDateOfHire(this.dateOfHire);
    }
    
    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    
    public void setDateOfHire(String dateOfHire) {
        this.dateOfHire = dateOfHire;
    }
}
