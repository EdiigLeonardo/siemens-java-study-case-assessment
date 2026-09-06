package com.siemens.booking.event;
import com.siemens.booking.repository.BookingRepository;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class BookingEventListener {
  private final BookingRepository repository;
  public BookingEventListener(BookingRepository repository) { this.repository = repository; }

  // BUG: @EventListener normal (sincrono) + publish acontece ANTES do
  // commit da transacao que criou o booking. O findById aqui corre na
  // mesma thread/transacao ainda a meio -> pode nao ver a linha (ou, se
  // ver, bloqueia o publisher ate isto terminar). Devia ser
  // @TransactionalEventListener(phase = AFTER_COMMIT) + @Async.
  @EventListener
  public void onCreated(BookingCreatedEvent event) {
    repository.findById(event.bookingId()).ifPresent(b ->
        System.out.println("Email enviado para " + b.getRequesterEmail()));
  }
}
