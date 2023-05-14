package be.vdab.toys.dto;

import be.vdab.toys.domain.OrderDetail;

import java.math.BigDecimal;

public record OrderDetailBeknopt(int ordered, BigDecimal priceEach, BigDecimal value, String productName) {
    public OrderDetailBeknopt(OrderDetail orderDetail) {
        this(orderDetail.getOrdered(), orderDetail.getPriceEach(), orderDetail.getValue(), orderDetail.getProductName());
    }
}
