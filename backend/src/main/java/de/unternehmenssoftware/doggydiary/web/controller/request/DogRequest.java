package de.unternehmenssoftware.doggydiary.web.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record DogRequest(
        @NotNull String name,
        @NotNull String breed,

        @NotNull @Min(value = 1, message = "Age cant be lower than 1")
        int age) {
}
