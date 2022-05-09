package com.ninjaone.backendinterviewproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ninjaone.backendinterviewproject.BackendInterviewProjectApplication;
import com.ninjaone.backendinterviewproject.model.Device;
import com.ninjaone.backendinterviewproject.model.DeviceType;
import com.ninjaone.backendinterviewproject.service.DeviceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {BackendInterviewProjectApplication.class})
@WebMvcTest(DeviceController.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
public class DeviceControllerTest {
    public static final Long ID = 1l;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    private DeviceService deviceService;

    private Device deviceEntity;

    @BeforeEach
    void setup(){
        DeviceType deviceType = new DeviceType();
        deviceType.setId(1l);
        deviceType.setName("Windows");
        deviceType.setCost(new BigDecimal(4));
        deviceEntity = new Device(ID, "value", deviceType);
    }

    @Test
    void getDeviceData() throws Exception {
        when(deviceService.get(ID)).thenReturn(Optional.of(deviceEntity));

        mockMvc.perform(get("/devices/" + ID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(objectMapper.writeValueAsString(deviceEntity)));
    }

    @Test
    void postDeviceData() throws Exception {
        when(deviceService.create(any())).thenReturn(deviceEntity);

        String deviceEntityString = objectMapper.writeValueAsString(deviceEntity);
        mockMvc.perform(post("/devices")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(deviceEntityString))
                .andExpect(status().isCreated())
                .andExpect(content().string(deviceEntityString));
    }

    @Test
    void deleteDeviceData() throws Exception {
        doNothing().when(deviceService).delete(ID);

        mockMvc.perform(delete("/devices/" + ID))
                .andExpect(status().isNoContent());
    }
}
