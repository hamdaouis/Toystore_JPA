package be.vdab.toys.services;

import be.vdab.toys.domain.Order;
import be.vdab.toys.domain.Status;
import be.vdab.toys.dto.OrderBeknopt;
import be.vdab.toys.dto.OrderMetProduct;
import be.vdab.toys.exceptions.OrderHeeftStatusShippedException;
import be.vdab.toys.exceptions.OrderNietGevondenException;
import be.vdab.toys.repositories.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static be.vdab.toys.domain.Status.CANCELLED;
import static be.vdab.toys.domain.Status.SHIPPED;

@Service
@Transactional(readOnly = true)
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(long id) {
        return orderRepository.findById(id).orElseThrow(OrderNietGevondenException::new);
    }

    public long count() {
        return orderRepository.count();
    }

    public List<OrderBeknopt> findUnshipped() {
        return orderRepository.findUnshipped(SHIPPED, CANCELLED);
    }

    public OrderMetProduct findOrderDetailById(long id) {
        var order = orderRepository.findOrderMetKlant(id);
        return new OrderMetProduct(order);
    }

    @Transactional
    public void ship(long id) {
        var order = orderRepository.findById(id).orElseThrow(OrderNietGevondenException::new);
        if (order.getStatus() == Status.SHIPPED) {
            throw new OrderHeeftStatusShippedException();
        }
        order.shipOrder();

    }
}
