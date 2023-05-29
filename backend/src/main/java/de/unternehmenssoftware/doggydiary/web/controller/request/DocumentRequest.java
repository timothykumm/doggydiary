package de.unternehmenssoftware.doggydiary.web.controller.request;

import jakarta.validation.constraints.NotNull;

public record DocumentRequest(@NotNull String title,
                              @NotNull String content,
                              @NotNull Long dogId) {
}
