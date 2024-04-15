package com.fallinnadim.inventoryservice.exception;
import com.fallinnadim.inventoryservice.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Map<String, ErrorResponse> handleInvalidArgument(MissingServletRequestParameterException exception) {
        Map<String, ErrorResponse> errorMap = new HashMap<>();
        errorMap.put("error", new ErrorResponse("request params " + exception.getParameterName() + " is required"));
        return errorMap;
    }
}
