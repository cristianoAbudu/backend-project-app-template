package com.ninjaone.backendinterviewproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Device not found.")
public class DeviceNotFoundExeption extends Exception{
    public DeviceNotFoundExeption(Long deviceId) {
    }
}
