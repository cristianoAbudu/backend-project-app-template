package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.database.SampleRepository;
import com.ninjaone.backendinterviewproject.exception.DuplicateEntityExeption;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.Sample;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceService {
    private final DeviceRepository repository;

    public DeviceService(DeviceRepository repository) {
        this.repository = repository;
    }

    public Device create(Device device) throws DuplicateEntityExeption {
        Device existingDevice = repository.findBySystemName(device.getSystemName());
        if(existingDevice != null){
            throw new DuplicateEntityExeption();
        }
        return repository.save(device);
    }
    public Device update(Device device) throws DuplicateEntityExeption {
        Device existingDevice = repository.findBySystemName(device.getSystemName());
        if(existingDevice != null && !existingDevice.getId().equals(device.getId())){
            throw new DuplicateEntityExeption();
        }
        return repository.save(device);
    }

    public Optional<Device> get(Long id){
        return repository.findById(id);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
