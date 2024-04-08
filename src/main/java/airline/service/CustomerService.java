package airline.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import airline.controller.model.CustomerData;
import airline.dao.CustomerDao;
import airline.entity.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor  
@Data
public class CustomerService {

	@Autowired
    private CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Transactional(readOnly = false)
    public CustomerData saveCustomer(CustomerData customerData) {
    	Customer customer = customerData.toCustomer();
    	Customer dbCustomer = customerDao.save(customer);
    	
    	return new CustomerData(dbCustomer);
    }
    
    @Transactional
    public CustomerData createCustomer(CustomerData customerData) {
        Customer customer = customerData.toCustomer();
        customer = customerDao.save(customer);
        return new CustomerData(customer);
    }

    @Transactional(readOnly = true)
    public CustomerData getCustomer(Long id) {
        Customer customer = findCustomerById(id);
        return new CustomerData(customer);
    }

    @Transactional(readOnly = true)
    public List<CustomerData> getAllCustomers() {
        List<Customer> customers = customerDao.findAll();
        return customers.stream().map(CustomerData::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = false)
    public void updateCustomer(Long id, CustomerData customerData) {
        Customer customer = customerDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                		"Customer with ID=" + id + " was not found"));

        customer.setFirstName(customerData.getFirstName());
        customer.setLastName(customerData.getLastName());
        customer.setEmail(customerData.getEmail());
        customer.setPhone(customerData.getPhone());
        
        customerDao.save(customer);
    }

	@Transactional
    public void deleteCustomer(Long id) {
        Customer customer = findCustomerById(id);
        customerDao.delete(customer);
    }

    private Customer findCustomerById(Long id) {
        return customerDao.findById(id)
                .orElseThrow(() -> new NoSuchElementException(
                		"Customer with ID=" + id + " was not found"));
    }

    @Transactional(readOnly = false)
    public void deleteCustomerById(Long id) {
        Customer customer = findCustomerById(id);
        customerDao.delete(customer);
    }
}
