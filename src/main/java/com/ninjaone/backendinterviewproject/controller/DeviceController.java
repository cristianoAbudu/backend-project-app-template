package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.exception.DuplicateEntityExeption;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService service;

    public DeviceController(DeviceService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Device post(@RequestBody Device sample) throws DuplicateEntityExeption {
        return service.create(sample);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Device put(@RequestBody Device sample) throws DuplicateEntityExeption {
        return service.update(sample);
    }

    @GetMapping("/{id}")
    private Device get(@PathVariable Long id){
        return service.get(id).orElseThrow();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id){
        service.delete(id);
    }
}
