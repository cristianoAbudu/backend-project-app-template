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

    /**
     *  Adds a service, name needs to be unique
     *
     *  Request body example:
     *
     *  <code>
     *      {
     *          "name" : "Service Name",
     *          "cost" : 10.99
     *      }
     *  </code>
     * @param service_
     * @return
     * @throws DuplicateEntityExeption
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Service post(@RequestBody Service service_) throws DuplicateEntityExeption {
        return service.create(service_);
    }

    /**
     *  Updates a service, name needs to be unique
     *
     *  Request body example:
     *
     *  <code>
     *      {
     *          "id": 1,
     *          "name" : "Service Name updated",
     *          "cost" : 10.99
     *      }
     *  </code>
     * @param service_
     * @return
     * @throws DuplicateEntityExeption
     */
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Service put(@RequestBody Service service_) throws DuplicateEntityExeption {
        return service.update(service_);
    }

    /**
     * Returns the service with the specified id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    private Service get(@PathVariable Long id){
        return service.get(id).orElseThrow();
    }

    /**
     * Deletes the service with specified id
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id){
        service.delete(id);
    }
}
