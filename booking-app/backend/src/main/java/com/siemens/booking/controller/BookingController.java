package com.siemens.booking.controller;
import com.siemens.booking.model.Booking;
import com.siemens.booking.service.BookingService;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.List;

@RestController @RequestMapping("/api/bookings")
public class BookingController {
  private final BookingService service;
  public BookingController(BookingService service) { this.service = service; }

  @PostMapping
  public Booking create(@RequestParam Long equipmentId, @RequestParam String email,
                         @RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
    return service.create(equipmentId, email, start, end);
  }

  @GetMapping public List<Booking> list() { return service.list(); }
  @DeleteMapping("/{id}") public void cancel(@PathVariable Long id) { service.cancel(id); }
}
