package com.microservices.users_services.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.microservices.users_services.models.Supplier;
import com.microservices.users_services.repositories.SupplierRepository;

@Service
public class SupplierService {
    //@Autowired
    private SupplierRepository supplierRepository;
    private int nextCustomId = 20;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier createSupplier(Supplier supplier) {
        supplier.setId(getNextCustomId());
        return supplierRepository.save(supplier);
    }

    public Optional<Supplier> getSupplierById(int id) {
        return supplierRepository.getById(id);
    }

    public List<Supplier> getSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier updateSupplier(String id, Supplier updateSupplier) {
        return supplierRepository.findById(id).map(supplier -> {
            if (updateSupplier.getName() != null)
                supplier.setName(updateSupplier.getName());
            if (updateSupplier.getEmail() != null)
                supplier.setEmail(updateSupplier.getEmail());
            if (updateSupplier.getPhone() != null)
                supplier.setPhone(updateSupplier.getPhone());
            if (updateSupplier.getAddress() != null)
                supplier.setAddress(updateSupplier.getAddress());

            return supplierRepository.save(supplier);
        }).orElseThrow(() -> new IllegalArgumentException("Supplier not found with id: " + id));
    }

    public Boolean deleteSupplier(String id) {
        if (supplierRepository.existsById(id)) {
            supplierRepository.deleteById(id);
            return true;
        } else {
            throw new IllegalArgumentException("Supplier not found with id: " + id);
        }
    }

    private synchronized int getNextCustomId() {
        return nextCustomId++;
    }
}
