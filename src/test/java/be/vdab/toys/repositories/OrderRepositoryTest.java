package be.vdab.toys.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
@DataJpaTest
class OrderRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String ORDERS = "orders";
    private final OrderRepository docentRepository;

    public OrderRepositoryTest(OrderRepository docentRepository) {
        this.docentRepository = docentRepository;
    }

    @Test
    void count() {
        assertThat(docentRepository.count())
                .isEqualTo(countRowsInTable(ORDERS));
    }
}