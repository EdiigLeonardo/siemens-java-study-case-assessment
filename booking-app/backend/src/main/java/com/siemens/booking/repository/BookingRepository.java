package com.siemens.booking.repository;
import com.siemens.booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

  // BUG (SQL injection): concatena o parametro na query nativa em vez de
  // usar bind (:email). Um requesterEmail tipo "x' OR '1'='1" devolve tudo.
  @Query(value = "SELECT * FROM booking WHERE requester_email = '" + "#{#email}" + "'", nativeQuery = true)
  List<Booking> findByEmailUnsafe(@Param("email") String email);

}
