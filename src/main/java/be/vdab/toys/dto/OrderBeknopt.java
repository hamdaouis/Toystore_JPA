package be.vdab.toys.dto;

import be.vdab.toys.domain.Status;

import java.time.LocalDate;

public interface OrderBeknopt {
    long getId();
    LocalDate getOrdered();
    LocalDate getRequired();
    String getCustomerName();
    Status getStatus();
}
