package be.vdab.toys.repositories;

import be.vdab.toys.domain.Order;
import be.vdab.toys.domain.Status;
import be.vdab.toys.dto.OrderBeknopt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    @Query("""
            select o.id as id, o.ordered as ordered, o.required as required, o.customer.name as customerName, o.status as status 
            from Order o
            where o.status not in (:shipped, :cancelled)
            order by o.id
            """)
    List<OrderBeknopt> findUnshipped(Status shipped, Status cancelled);
    @Query("select o from Order o join fetch o.customer c join fetch c.country where o.id = :id")
    Order findOrderMetKlant(long id);
}
