package com.kodlama.io.rentacar.core.util.results;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.internal.engine.MethodValidationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.time.LocalDate;

@Getter
@Setter
public class ExceptionResult<T> {
    private LocalDate timestamp;
    private T message;
    private String type;
    private Integer httpStatus;


    public ExceptionResult(T message,String type,Integer httpStatus) {
        this.timestamp = LocalDate.now();
        this.message = message;
        this.type = type;
        this.httpStatus = httpStatus;
    }

}
