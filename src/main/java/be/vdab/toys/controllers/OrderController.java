package be.vdab.toys.controllers;

import be.vdab.toys.domain.Order;
import be.vdab.toys.dto.OrderBeknopt;
import be.vdab.toys.dto.OrderMetProduct;
import be.vdab.toys.exceptions.OrderWerdDoorAndereGebruikerGewijzigdException;
import be.vdab.toys.services.OrderService;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("all")
    List<Order> findAll() {
        return orderService.findAll();
    }

    @GetMapping("aantal")
    long count() {
        return orderService.count();
    }

    @GetMapping("unshipped")
    List<OrderBeknopt> findOrdersNotShippedOrCancelled() {
        return orderService.findUnshipped();
    }

    @GetMapping("{id}")
    OrderMetProduct findOrderDetailById(@PathVariable long id) {
        return orderService.findOrderDetailById(id);
    }

    @PostMapping("{id}/shippings")
    void shipOrder(@PathVariable long id) {
        try {
            orderService.ship(id);
        } catch (ObjectOptimisticLockingFailureException ex) {
            throw new OrderWerdDoorAndereGebruikerGewijzigdException();
        }
    }
}
