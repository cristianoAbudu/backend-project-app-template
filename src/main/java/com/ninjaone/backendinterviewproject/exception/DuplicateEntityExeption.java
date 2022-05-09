package com.ninjaone.backendinterviewproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "Duplicated Entity not allowed.")
public class DuplicateEntityExeption extends Exception{
}
