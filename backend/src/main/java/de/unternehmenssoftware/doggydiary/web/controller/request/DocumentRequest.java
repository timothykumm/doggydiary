package de.unternehmenssoftware.doggydiary.web.controller.request;

public record DocumentRequest(String title,
                              String content,
                              Long dogId) {
}
