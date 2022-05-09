package com.ninjaone.backendinterviewproject.controller;

import com.ninjaone.backendinterviewproject.dto.CustomerDeviceDTO;
import com.ninjaone.backendinterviewproject.exception.DeviceNotFoundExeption;
import com.ninjaone.backendinterviewproject.exception.DuplicateEntityExeption;
import com.ninjaone.backendinterviewproject.exception.ServiceNotFoundExeption;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.service.CostService;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/cost")
public class CostController {
    private final CostService service;

    public CostController(CostService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.CREATED)
    private BigDecimal post(@RequestBody List<CustomerDeviceDTO> customerDeviceDTOList) throws DeviceNotFoundExeption, ServiceNotFoundExeption {
        return service.calculate(customerDeviceDTOList);
    }

}
