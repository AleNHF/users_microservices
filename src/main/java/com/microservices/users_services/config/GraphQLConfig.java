package com.microservices.users_services.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import com.microservices.users_services.resolvers.CustomerResolver;
import com.microservices.users_services.resolvers.SupplierResolver;
import com.microservices.users_services.resolvers.UserResolver;

@Configuration
public class GraphQLConfig {
    
    @Bean
    RuntimeWiringConfigurer runtimeWiringConfigurer(UserResolver userResolver, CustomerResolver customerResolver, SupplierResolver supplierResolver) {
        return wiringBuilder -> wiringBuilder
            .type("Query", typeWiring -> typeWiring
                .dataFetcher("users", userResolver.getUsers())
                .dataFetcher("user", userResolver.getUserById())
                .dataFetcher("customers", customerResolver.getCustomers())
                .dataFetcher("customer", customerResolver.getCustomerById())
                .dataFetcher("suppliers", supplierResolver.getSuppliers())
                .dataFetcher("supplier", supplierResolver.getSupplierById())
            )
            .type("Mutation", typeWiring -> typeWiring
                .dataFetcher("createUser", userResolver.createUser())
                .dataFetcher("updateUser", userResolver.updateUser())
                .dataFetcher("deleteUser", userResolver.deleteUser())
                .dataFetcher("loginUser", userResolver.loginUser())
                .dataFetcher("createCustomer", customerResolver.createCustomer())
                .dataFetcher("updateCustomer", customerResolver.updateCustomer())
                .dataFetcher("deleteCustomer", customerResolver.deleteCustomer())
                .dataFetcher("createSupplier", supplierResolver.createSupplier())
                .dataFetcher("updateSupplier", supplierResolver.updateSupplier())
                .dataFetcher("deleteSupplier", supplierResolver.deleteSupplier())
            );
    }
}
