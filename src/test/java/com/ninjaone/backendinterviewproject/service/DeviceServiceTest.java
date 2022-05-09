package com.ninjaone.backendinterviewproject.service;

import com.ninjaone.backendinterviewproject.database.DeviceRepository;
import com.ninjaone.backendinterviewproject.exception.DeviceTypeNotFoundExeption;
import com.ninjaone.backendinterviewproject.exception.DuplicateEntityExeption;
import com.ninjaone.backendinterviewproject.model.Device;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {
    public static final Long ID = 1l;

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService testObject;

    private Device deviceEntity;

    @BeforeEach
    void setup(){
        deviceEntity = new Device(ID, "value", null);
    }

    @Test
    void getDeviceData() {
        when(deviceRepository.findById(ID)).thenReturn(Optional.of(deviceEntity));
        Optional<Device> deviceEntityOptional = testObject.get(ID);
        Device actualEntity = deviceEntityOptional.orElse(null);
        assert actualEntity != null;
        assertEquals(deviceEntity.getId(), actualEntity.getId());
    }

    @Test
    void saveDeviceData() throws DeviceTypeNotFoundExeption, DuplicateEntityExeption {
        when(deviceRepository.save(deviceEntity)).thenReturn(deviceEntity);
        assertEquals(deviceEntity, testObject.create(deviceEntity));
    }

    @Test
    void deleteDeviceData(){
        doNothing().when(deviceRepository).deleteById(ID);
        testObject.delete(ID);
        Mockito.verify(deviceRepository, times(1)).deleteById(ID);
    }
}
