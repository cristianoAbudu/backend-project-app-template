package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.database.ServiceRepository;
import com.ninjaone.backendinterviewproject.dto.CustomerDeviceDTO;
import com.ninjaone.backendinterviewproject.exception.DeviceNotFoundExeption;
import com.ninjaone.backendinterviewproject.exception.DuplicateEntityExeption;
import com.ninjaone.backendinterviewproject.exception.ServiceNotFoundExeption;
import com.ninjaone.backendinterviewproject.model.Device;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CostService {
    private final DeviceRepository deviceRepository;

    private final ServiceRepository serviceRepository;

    public CostService(DeviceRepository deviceRepository, ServiceRepository serviceRepository) {
        this.deviceRepository = deviceRepository;
        this.serviceRepository = serviceRepository;
    }

    public BigDecimal calculate(List<CustomerDeviceDTO> customerDeviceDTOList) throws DeviceNotFoundExeption, ServiceNotFoundExeption {
        BigDecimal cost = BigDecimal.ZERO;
        for(CustomerDeviceDTO customerDeviceDTO : customerDeviceDTOList){
            Optional<Device> deviceOptional = deviceRepository.findById(customerDeviceDTO.getDeviceId());
            if(deviceOptional.isEmpty()){
                throw new DeviceNotFoundExeption(customerDeviceDTO.getDeviceId());
            }
            Device device = deviceOptional.get();
            cost = cost.add(device.getType().getCost());
            for(Long serviceId : customerDeviceDTO.getServiceIdList()){
                Optional<com.ninjaone.backendinterviewproject.model.Service> serviceOptional = serviceRepository.findById(serviceId);
                if(serviceOptional.isEmpty()){
                    throw new ServiceNotFoundExeption(serviceId);
                }
                com.ninjaone.backendinterviewproject.model.Service service = serviceOptional.get();
                cost = cost.add(service.getCost());
            }
        }
        return cost;
    }
}
