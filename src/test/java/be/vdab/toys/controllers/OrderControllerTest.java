package be.vdab.toys.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class OrderControllerTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String ORDERS = "orders";
    private final MockMvc mockMvc;

    public OrderControllerTest(MockMvc mockMvc) {
        this.mockMvc = mockMvc;
    }

    @Test
    void count() throws Exception {
        mockMvc.perform(get("/orders/aantal"))
                .andExpectAll(
                        status().isOk(),
                        jsonPath("$").value(countRowsInTable(ORDERS)));
    }
}