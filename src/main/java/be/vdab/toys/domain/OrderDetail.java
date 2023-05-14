package be.vdab.toys.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
@Access(AccessType.FIELD)
public class OrderDetail {
    private int ordered;
    private BigDecimal priceEach;
    @ManyToOne(optional = false)
    @JoinColumn(name = "productId")
    private Product product;

    public OrderDetail(int ordered, BigDecimal priceEach, Product product) {
        this.ordered = ordered;
        this.priceEach = priceEach;
        this.product = product;
    }
    protected OrderDetail(){}

    public int getOrdered() {
        return ordered;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public Product getProduct() {
        return product;
    }
    public String getProductName(){
        return product.getName();
    }

    public BigDecimal getValue() {
        return priceEach.multiply(BigDecimal.valueOf(ordered));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetail that)) return false;
        return ordered == that.ordered && Objects.equals(priceEach, that.priceEach) && Objects.equals(product, that.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ordered, priceEach, product);
    }
}
