package be.vdab.toys.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "products")
public class Product {
    @Id
    private long id;
    private String name;
    private String scale;
    private String description;
    private int inStock;
    private int inOrder;
    private BigDecimal price;
    @ManyToOne(optional = false)
    @JoinColumn(name = "productlineId")
    private ProductLine productLine;
    @Version
    private long version;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScale() {
        return scale;
    }

    public String getDescription() {
        return description;
    }

    public int getInStock() {
        return inStock;
    }

    public int getInOrder() {
        return inOrder;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public ProductLine getProductLine() {
        return productLine;
    }

    public long getVersion() {
        return version;
    }
    public void verlaagInStock(int aantal){
        inStock -= aantal;
    }
    public void verlaagInOrder(int aantal){
        inOrder -= aantal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product product)) return false;
        return Objects.equals(name, product.name) && Objects.equals(description, product.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description);
    }
}
