package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.exception.DeviceTypeNotFoundExeption;
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

    /**
     * Adds a device where system name needs to be unique and type is one of the
     * @see com.ninjaone.backendinterviewproject.model.DeviceType entity id
     *
     * Request body example:
     * <code>
     *     {
     *     "systemName" : "Windows Device 1",
     *          "type" : {
     *            "id" : 1
     *          }
     *      }
     * </code>
     * @param device
     * @return
     * @throws DuplicateEntityExeption
     * @throws DeviceTypeNotFoundExeption
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Device post(@RequestBody Device device) throws DuplicateEntityExeption, DeviceTypeNotFoundExeption {
        return service.create(device);
    }

    /**
     * Updates a device where system name needs to be unique and type is one of the
     * @see com.ninjaone.backendinterviewproject.model.DeviceType entity id
     *
     * Request body example:
     * <code>
     *     {
     *     "id" : 1,
     *     "systemName" : "Windows Device 1 updated",
     *          "type" : {
     *            "id" : 1
     *          }
     *      }
     * </code>
     * 
     * @param device
     * @return
     * @throws DuplicateEntityExeption
     * @throws DeviceTypeNotFoundExeption
     */
    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    private Device put(@RequestBody Device device) throws DuplicateEntityExeption, DeviceTypeNotFoundExeption {
        return service.update(device);
    }

    /**
     * Returns the device with the specified id
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    private Device get(@PathVariable Long id){
        return service.get(id).orElseThrow();
    }

    /**
     * Deletes the device with the specified id
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id){
        service.delete(id);
    }
}
