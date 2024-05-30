package it.device.device_management.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MyBadRequestException extends RuntimeException{
    public MyBadRequestException(String message) {
        super(message);
    }
}
