package com.ninjaone.backendinterviewproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Device Type not found.")
public class DeviceTypeNotFoundExeption extends Exception{
    public DeviceTypeNotFoundExeption(Long deviceId) {
    }
}
