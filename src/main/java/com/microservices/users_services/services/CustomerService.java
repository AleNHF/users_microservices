package com.microservices.users_services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservices.users_services.models.Customer;
import com.microservices.users_services.repositories.CustomerRepository;

@Service
public class CustomerService {
    //@Autowired
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(String id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    public Customer update(String id, Customer customer) {
        customer.setId(id);
        return customerRepository.save(customer);
    }

    public Customer updateCustomer(String id, Customer updateCustomer) {
        return customerRepository.findById(id).map(customer -> {
            if (updateCustomer.getName() != null)
                customer.setName(updateCustomer.getName());
            if (updateCustomer.getEmail() != null)
                customer.setEmail(updateCustomer.getEmail());
            if (updateCustomer.getPhone() != null)
                customer.setPhone(updateCustomer.getPhone());

            return customerRepository.save(customer);
        }).orElseThrow(() -> new IllegalArgumentException("Customer not found with id: " + id));
    }

    public Boolean deleteCustomer(String id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        } else {
            throw new IllegalArgumentException("Customer not found with id: " + id);
        }
    }
}
