package be.vdab.toys.domain;

import be.vdab.toys.exceptions.OnvoldoendeVoorraadException;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private long id;
    private LocalDate ordered;
    private LocalDate required;
    private LocalDate shipped;
    private String comments;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId")
    private Customer customer;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ElementCollection
    @CollectionTable(name = "orderdetails",
            joinColumns = @JoinColumn(name = "orderId") )
    private Set<OrderDetail> orderDetails;
    public BigDecimal getTotaalValue(){
        return orderDetails.stream().map(OrderDetail::getValue).reduce(BigDecimal.ZERO, BigDecimal::add);
    }
    @Version
    private long version;

    public Order(LocalDate ordered, LocalDate required, LocalDate shipped, String comments, Customer customer, Status status, Set<OrderDetail> orderDetails, long version) {
        this.ordered = ordered;
        this.required = required;
        this.shipped = shipped;
        this.comments = comments;
        this.customer = customer;
        this.status = status;
        this.orderDetails = new LinkedHashSet<OrderDetail>();
        this.version = version;
    }
    protected Order(){}

    public long getId() {
        return id;
    }

    public LocalDate getOrdered() {
        return ordered;
    }

    public LocalDate getRequired() {
        return required;
    }

    public LocalDate getShipped() {
        return shipped;
    }

    public String getComments() {
        return comments;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Status getStatus() {
        return status;
    }
    public Set<OrderDetail> getOrderDetails() {
        return Collections.unmodifiableSet(orderDetails);
    }

    public long getVersion() {
        return version;
    }
    public void shipOrder(){
        status = Status.SHIPPED;
        shipped = LocalDate.now();
        for (var orderDetail : orderDetails) {
            var product = orderDetail.getProduct();
            var aantal = orderDetail.getOrdered();
            var voorraad = product.getInStock();
            if (aantal > voorraad) {
                throw new OnvoldoendeVoorraadException();
            }
            product.verlaagInStock(aantal);
            product.verlaagInOrder(aantal);

        }
    }
}
