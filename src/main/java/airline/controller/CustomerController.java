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

import airline.controller.model.CustomerData;
import airline.service.CustomerService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/customers")
@Slf4j
@Data
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerData createCustomer(@RequestBody CustomerData customerData) {
        log.info("Creating a Customer {}", customerData);
        return customerService.createCustomer(customerData);
    }

    @GetMapping("/{id}")
    public CustomerData getCustomer(@PathVariable Long id) {
    	log.info("Retrieving Customer with ID={}", id);
        return customerService.getCustomer(id);
    }
    
    @GetMapping
    public List<CustomerData> getAllCustomers() {
    	log.info("Retrieving all Customers");
        return customerService.getAllCustomers();
    }
    
    @PutMapping("/{id}")
    @ResponseStatus(code = HttpStatus.OK)
    public void updateCustomer(@PathVariable Long id, 
    		@RequestBody CustomerData customerData) {
    	log.info("Updating Customer {}", customerData);
    	customerService.updateCustomer(id, customerData);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteCustomerById(@PathVariable Long id) {
        log.info("Deleting Customer with ID={}", id);
        customerService.deleteCustomerById(id);
    }
}
