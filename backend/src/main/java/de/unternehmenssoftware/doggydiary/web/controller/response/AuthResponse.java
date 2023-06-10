package de.unternehmenssoftware.doggydiary.web.controller.response;

public record AuthResponse(
        String appToken,
        String openai
) {
}
