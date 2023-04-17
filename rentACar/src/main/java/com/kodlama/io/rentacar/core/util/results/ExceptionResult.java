package com.kodlama.io.rentacar.core.util.results;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.internal.engine.MethodValidationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
@Setter
public class ExceptionResult<T> {
    private String type;
    private Integer httpStatus;
    private String message;



    public ExceptionResult(Class<T> type,Integer httpStatus ,String message) {
        this.type = convertToUpperCaseWithUnderscores(type.getSimpleName());
        this.httpStatus = httpStatus;
        this.message = message;
    }

    private String convertToUpperCaseWithUnderscores(String camelCaseString) {
        return camelCaseString.replaceAll("(.)(\\p{Upper})", "$1_$2").toUpperCase();
    }
}
