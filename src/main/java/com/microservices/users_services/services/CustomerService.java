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
    private int nextCustomId = 2000;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        customer.setId(getNextCustomId());
        return customerRepository.save(customer);
    }

    public Optional<Customer> getCustomerById(int id) {
        return customerRepository.getById(id);
    }

    public List<Customer> getCustomers() {
        return customerRepository.findAll();
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

    private synchronized int getNextCustomId() {
        return nextCustomId++;
    }
}
