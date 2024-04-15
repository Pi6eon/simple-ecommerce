package com.fallinnadim.orderservice.exception;

import com.fallinnadim.orderservice.dto.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestControllerAdvice
@Slf4j
public class GlobalException {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleInvalidArgument(MethodArgumentNotValidException exception) {
        Map<String, String> errorMap = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return errorMap;
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(SQLException.class)
    public Map<String, ErrorResponse> handlePostgresException(SQLException exception) {
        Map<String, ErrorResponse> errorMap = new HashMap<>();
        switch (exception.getSQLState()) {
            case "23505":
                // Parse the field error from message
                String errorMessage = exception.getMessage();
                Pattern pattern = Pattern.compile("Detail: Key \\((.*?)\\)=");
                Matcher matcher = pattern.matcher(errorMessage);
                if (matcher.find()) {
                    String field = matcher.group(1);
                    errorMap.put("error", new ErrorResponse(field + " field must be unique"));
                }
                return errorMap;
            default:
                return errorMap;

        }

    }
}
