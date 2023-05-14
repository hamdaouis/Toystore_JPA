package be.vdab.toys.dto;

import be.vdab.toys.domain.Order;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

public record OrderMetProduct(long id, LocalDate ordered, LocalDate required, String customerName, String countryName,
                              BigDecimal value, Set<OrderDetailBeknopt> details) {
    public OrderMetProduct(Order order) {
        this(order.getId(), order.getOrdered(), order.getRequired(), order.getCustomer().getName(),
                order.getCustomer().getCountry().getName(), order.getTotaalValue(), order.getOrderDetails().stream().map(OrderDetailBeknopt::new).collect(Collectors.toSet()));
    }
}
