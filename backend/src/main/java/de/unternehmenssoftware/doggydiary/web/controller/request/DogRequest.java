package de.unternehmenssoftware.doggydiary.web.controller.request;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record DogRequest(
        @NotNull String name,
        @NotNull String breed,

        @NotNull Date birthdate) {
}
