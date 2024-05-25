package com.microservices.users_services.resolvers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.microservices.users_services.models.Customer;
import com.microservices.users_services.services.CustomerService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetcher;

@Component
public class CustomerResolver implements GraphQLQueryResolver {
    private final CustomerService customerService;

    public CustomerResolver(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    public DataFetcher<List<Customer>> getCustomers() {
        return dataFetchingEnvironment -> customerService.getCustomers();
    }

    public DataFetcher<Optional<Customer>> getCustomerById() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return customerService.getCustomerById(id);
        };
    }

    public DataFetcher<Customer> createCustomer() {
        return dataFetchingEnvironment -> {
            String name = dataFetchingEnvironment.getArgument("name");
            String phone = dataFetchingEnvironment.getArgument("phone");
            String email = dataFetchingEnvironment.getArgument("email");

            Customer customer = new Customer();
            customer.setName(name);
            customer.setPhone(phone);
            customer.setEmail(email);

            return customerService.createCustomer(customer);
        };
    }

    public DataFetcher<Customer> updateCustomer() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            String name = dataFetchingEnvironment.getArgument("name");
            String phone = dataFetchingEnvironment.getArgument("phone");
            String email = dataFetchingEnvironment.getArgument("email");
            Customer customer = new Customer();
            customer.setName(name);
            customer.setPhone(phone);
            customer.setEmail(email);
            return customerService.updateCustomer(id, customer);
        };
    }

    public DataFetcher<Boolean> deleteCustomer() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return customerService.deleteCustomer(id);
        };
    }
}
