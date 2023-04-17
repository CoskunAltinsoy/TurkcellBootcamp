package com.kodlama.io.rentacar.core.util.results;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.MethodArgumentNotValidException;

@Getter
@Setter
public class ExceptionDataResult<T> extends ExceptionResult{
    private T data;
    public ExceptionDataResult(T data,
                               Class<MethodArgumentNotValidException> type,
                               Integer httpStatus,
                               String message) {
        super(type, httpStatus, message);
        this.data = data;
    }
}
