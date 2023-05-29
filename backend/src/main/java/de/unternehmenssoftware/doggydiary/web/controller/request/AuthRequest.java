package de.unternehmenssoftware.doggydiary.web.controller.request;

import jakarta.validation.constraints.NotNull;

public record AuthRequest(
         @NotNull String email,
         @NotNull String forename,
         @NotNull String surname,
         @NotNull String password) {

}
