package airline.controller.model;

import airline.entity.Customer;
import lombok.Data;

@Data
public class CustomerData {
    private Long customerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private Long airlineId;

    public CustomerData() {}

    public CustomerData(Long customerId, String firstName, 
    		String lastName, String email,String phone, Long airlineId) {
    	this.airlineId = airlineId;
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;        
    }
    
    public CustomerData(Customer customer) {
    	this.customerId = customer.getCustomerId();
    	this.firstName = customer.getFirstName();
    	this.lastName = customer.getLastName();
    	this.email = customer.getEmail();
    	this.phone = customer.getPhone();
    }
    
    public Customer toCustomer() {
    	Customer customer = new Customer();
    	customer.setCustomerId(this.customerId);
    	customer.setFirstName(this.firstName);
    	customer.setLastName(this.lastName);
    	customer.setEmail(this.email);
    	customer.setPhone(this.phone);
    }
    

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
    
    public void setFirstname(String firstName) {
        this.firstName = firstName;
    }
    
    public void setlastName(String lastName) {
        this.lastName = lastName;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setPhone(String phone) {
        this.phone = phone;
    }    
}
