package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.database.DeviceTypeRepository;
import com.ninjaone.backendinterviewproject.exception.DeviceTypeNotFoundExeption;
import com.ninjaone.backendinterviewproject.exception.DuplicateEntityExeption;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceService {
    private final DeviceRepository repository;

    private final DeviceTypeRepository deviceTypeRepository;

    public DeviceService(DeviceRepository repository, DeviceTypeRepository deviceTypeRepository) {
        this.repository = repository;
        this.deviceTypeRepository =  deviceTypeRepository;
    }

    public Device create(Device device) throws DuplicateEntityExeption, DeviceTypeNotFoundExeption {
        Device existingDevice = repository.findBySystemName(device.getSystemName());
        if(existingDevice != null){
            throw new DuplicateEntityExeption();
        }
        populateDeviceType(device);
        return repository.save(device);
    }
    public Device update(Device device) throws DuplicateEntityExeption, DeviceTypeNotFoundExeption {
        Device existingDevice = repository.findBySystemName(device.getSystemName());
        if(existingDevice != null && !existingDevice.getId().equals(device.getId())){
            throw new DuplicateEntityExeption();
        }
        if(!device.getType().getId().equals(existingDevice.getType().getId())){
            populateDeviceType(device);
        }
        return repository.save(device);
    }

    private void populateDeviceType(Device device) throws DeviceTypeNotFoundExeption {
        Optional<DeviceType> deviceTypeOptional = deviceTypeRepository.findById(device.getType().getId());
        if(deviceTypeOptional.isEmpty()){
            throw new DeviceTypeNotFoundExeption(device.getType().getId());
        }
        device.setType(deviceTypeOptional.get());
    }

    public Optional<Device> get(Long id){
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
