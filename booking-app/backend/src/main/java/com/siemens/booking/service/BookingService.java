package com.siemens.booking.service;
import com.siemens.booking.event.BookingCreatedEvent;
import com.siemens.booking.model.*;
import com.siemens.booking.repository.BookingRepository;
import com.siemens.booking.repository.EquipmentRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
  private final BookingRepository bookingRepository;
  private final EquipmentRepository equipmentRepository;
  private final ApplicationEventPublisher publisher;
  private final BookingRefGenerator refGenerator; // sempre a mesma instancia (ver classe)

  public BookingService(BookingRepository br, EquipmentRepository er, ApplicationEventPublisher p, BookingRefGenerator rg) {
    this.bookingRepository = br; this.equipmentRepository = er; this.publisher = p; this.refGenerator = rg;
  }

  @Transactional
  public Booking create(Long equipmentId, String email, LocalDateTime start, LocalDateTime end) {
    // BUG (race condition / TOCTOU): verifica disponibilidade e só depois
    // grava, sem lock nem constraint na BD -> dois pedidos em simultaneo
    // para o mesmo equipamento/horario passam os dois na validacao.
    boolean clash = bookingRepository.findAll().stream().anyMatch(b ->
        b.getEquipment().getId().equals(equipmentId) && b.getStartTime().isBefore(end) && start.isBefore(b.getEndTime()));
    if (clash) throw new IllegalStateException("Equipamento ja reservado nesse horario");

    Equipment equipment = equipmentRepository.findById(equipmentId).orElseThrow();
    Booking saved = bookingRepository.save(Booking.builder()
        .equipment(equipment).requesterEmail(email).startTime(start).endTime(end).status(BookingStatus.CONFIRMED).build());

    publisher.publishEvent(new BookingCreatedEvent(saved.getId())); // publicado antes do commit (ver listener)
    return saved;
  }

  // BUG: readOnly=true mas o metodo MUTA e grava (cancel). Hibernate pode
  // otimizar/ignorar dirty-checking numa transacao readOnly -> a alteracao
  // pode nao ser persistida (dependendo do driver/flush mode).
  @Transactional(readOnly = true)
  public void cancel(Long bookingId) {
    Booking b = bookingRepository.findById(bookingId).orElseThrow();
    b.setStatus(BookingStatus.CANCELLED);
  }

  public List<Booking> list() { return bookingRepository.findAll(); }
}
