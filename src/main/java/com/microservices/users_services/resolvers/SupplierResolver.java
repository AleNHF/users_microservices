package com.microservices.users_services.resolvers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import com.microservices.users_services.models.Supplier;
import com.microservices.users_services.services.SupplierService;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.schema.DataFetcher;

@Component
public class SupplierResolver implements GraphQLQueryResolver {
    private final SupplierService supplierService;

    public SupplierResolver(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public DataFetcher<List<Supplier>> getSuppliers() {
        return dataFetchingEnvironment -> supplierService.getSuppliers();
    }

    public DataFetcher<Optional<Supplier>> getSupplierById() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return supplierService.getSupplierById(id);
        };
    }

    public DataFetcher<Supplier> createSupplier() {
        return dataFetchingEnvironment -> {
            String name = dataFetchingEnvironment.getArgument("name");
            String phone = dataFetchingEnvironment.getArgument("phone");
            String email = dataFetchingEnvironment.getArgument("email");
            String address = dataFetchingEnvironment.getArgument("address");

            Supplier supplier = new Supplier();
            supplier.setName(name);
            supplier.setPhone(phone);
            supplier.setEmail(email);
            supplier.setAddress(address);

            return supplierService.createSupplier(supplier);
        };
    }

    public DataFetcher<Supplier> updateSupplier() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            String name = dataFetchingEnvironment.getArgument("name");
            String phone = dataFetchingEnvironment.getArgument("phone");
            String email = dataFetchingEnvironment.getArgument("email");
            String address = dataFetchingEnvironment.getArgument("address");

            Supplier supplier = new Supplier();
            supplier.setName(name);
            supplier.setPhone(phone);
            supplier.setEmail(email);
            supplier.setAddress(address);

            return supplierService.updateSupplier(id, supplier);
        };
    }

    public DataFetcher<Boolean> deleteSupplier() {
        return dataFetchingEnvironment -> {
            String id = dataFetchingEnvironment.getArgument("id");
            return supplierService.deleteSupplier(id);
        };
    }
}
