package com.kodlama.io.rentacar.core.configuration.exception;

import com.kodlama.io.rentacar.core.exceptions.BusinessException;
import com.kodlama.io.rentacar.core.util.results.ExceptionDataResult;
import com.kodlama.io.rentacar.core.util.results.ExceptionResult;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalRestExceptionHandler {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)

    public ExceptionResult<BusinessException> handleBusinessException(BusinessException exception) {
        return new ExceptionResult<BusinessException>(
                BusinessException.class,
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                exception.getMessage()
        );
    }

    @ExceptionHandler
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    private ExceptionDataResult<Object> handleValidationException(MethodArgumentNotValidException exception) {
        Map<String, String> validationErrors = new HashMap<String, String>();
        for (FieldError fieldError : exception.getBindingResult().getFieldErrors()) {
            validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }

        ExceptionDataResult<Object> exceptionResult = new ExceptionDataResult<Object>(
                validationErrors,
                MethodArgumentNotValidException.class,
                HttpStatus.BAD_REQUEST.value(),
                exception.getMessage()
                );
        return exceptionResult;
    }

}
