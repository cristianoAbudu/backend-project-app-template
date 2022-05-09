package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.exception.DuplicateEntityExeption;
import com.ninjaone.backendinterviewproject.model.Service;

import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {
    private final ServiceRepository repository;

    public ServiceService(ServiceRepository repository) {
        this.repository = repository;
    }

    public Service create(Service service) throws DuplicateEntityExeption {
        Service existingService = repository.findByName(service.getName());
        if(existingService != null){
            throw new DuplicateEntityExeption();
        }
        return repository.save(service);
    }
    public Service update(Service service) throws DuplicateEntityExeption {
        Service existingService = repository.findByName(service.getName());
        if(existingService != null && !existingService.getId().equals(service.getId())){
            throw new DuplicateEntityExeption();
        }
        return repository.save(service);
    }

    public Optional<Service> get(Long id){
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
