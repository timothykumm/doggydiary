package de.unternehmenssoftware.doggydiary.web.controller.request;

public record DogRequest(
        String name,
        String breed,
        int age) {
}
