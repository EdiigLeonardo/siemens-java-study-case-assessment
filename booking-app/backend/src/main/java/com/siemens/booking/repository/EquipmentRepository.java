package com.siemens.booking.repository;
import com.siemens.booking.model.Equipment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {
  // BUG: JOIN FETCH da colecao "bookings" sem DISTINCT -> Equipment repetido
  // uma vez por cada Booking (produto cartesiano).
  @Query("SELECT e FROM Equipment e JOIN FETCH e.bookings")
  List<Equipment> findAllWithBookings();
}
