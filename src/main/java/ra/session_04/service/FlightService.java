package ra.session_04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import ra.session_04.entity.Flight;
import ra.session_04.repository.FlightRepository;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

    public Page<Flight> getFlights(String departure, String destination, int page) {
        Pageable pageable = PageRequest.of(page, 5);
        return flightRepository.findByDepartureContainingAndDestinationContaining(departure, destination, pageable);
    }

    public Flight getFlightById(Long id) {
        return flightRepository.findById(id).orElse(null);
    }

    public void saveFlight(Flight flight) {
        flightRepository.save(flight);
    }
}