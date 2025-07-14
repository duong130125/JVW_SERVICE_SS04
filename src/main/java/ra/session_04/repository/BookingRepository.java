package ra.session_04.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ra.session_04.entity.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByCustomerPhone(String phone);
}