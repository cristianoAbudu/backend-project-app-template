package com.ninjaone.backendinterviewproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.PRECONDITION_FAILED, reason = "Device not found.")
public class ServiceNotFoundExeption extends Exception{
    public ServiceNotFoundExeption(Long deviceId) {
    }
}
