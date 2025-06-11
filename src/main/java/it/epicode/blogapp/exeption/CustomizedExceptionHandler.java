package it.epicode.blogapp.exeption;

import it.epicode.blogapp.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class CustomizedExceptionHandler {
    @ExceptionHandler(AutoreNotFoundExpetion.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError autoreNotFoundExeptionHandler(AutoreNotFoundExpetion a){
        ApiError error = new ApiError();
        error.setMessage(a.getMessage());
        error.setDataErrore(LocalDateTime.now());
        return error;
    }

    @ExceptionHandler(BlogNotFoundExeption.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiError postNotFoundExeptionHandler(BlogNotFoundExeption b){
        ApiError error = new ApiError();
        error.setMessage(b.getMessage());
        error.setDataErrore(LocalDateTime.now());
        return error;
    }
}
