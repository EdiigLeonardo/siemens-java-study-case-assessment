package com.siemens.booking.service;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import java.util.UUID;

// BUG: @Scope prototype, mas quem o usa (BookingService) e singleton e so
// recebe a instancia UMA vez na injecao -> "prototype" nunca cria uma
// instancia nova por uso, o campo fica sempre com a mesma instancia.
@Component
@Scope("prototype")
public class BookingRefGenerator {
  private final String ref = UUID.randomUUID().toString();
  public String next() { return ref; }
}
