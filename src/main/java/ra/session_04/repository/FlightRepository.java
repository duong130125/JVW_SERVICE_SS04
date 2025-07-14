package ra.session_04.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ra.session_04.entity.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Page<Flight> findByDepartureContainingAndDestinationContaining(String departure, String destination, Pageable pageable);
}