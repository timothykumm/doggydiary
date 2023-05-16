package de.unternehmenssoftware.doggydiary.web.controller.request;

public record AuthRequest(
         String email,
         String forename,
         String surname,
         String password) {

}
