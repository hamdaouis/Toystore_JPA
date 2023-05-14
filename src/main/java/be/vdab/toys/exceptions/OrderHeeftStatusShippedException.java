package be.vdab.toys.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OrderHeeftStatusShippedException extends RuntimeException{
    public OrderHeeftStatusShippedException() {
        super("Order heeft status SHIPPING");
    }
}
