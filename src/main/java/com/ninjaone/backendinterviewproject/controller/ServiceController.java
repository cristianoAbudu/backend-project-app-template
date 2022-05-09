package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.exception.DuplicateEntityExeption;
import com.ninjaone.backendinterviewproject.model.Service;
import com.ninjaone.backendinterviewproject.service.ServiceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/services")
public class ServiceController {
    private final ServiceService service;

    public ServiceController(ServiceService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Service post(@RequestBody Service service_) throws DuplicateEntityExeption {
        return service.create(service_);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Service put(@RequestBody Service service_) throws DuplicateEntityExeption {
        return service.update(service_);
    }

    @GetMapping("/{id}")
    private Service get(@PathVariable Long id){
        return service.get(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id){
        service.delete(id);
    }
}
