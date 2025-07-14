package ra.session_04.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.session_04.BookingStatus;
import ra.session_04.entity.Booking;
import ra.session_04.entity.Flight;
import ra.session_04.repository.BookingRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public void bookFlight(Flight flight, String name, String phone) {
        Booking booking = Booking.builder()
                .flight(flight)
                .customerName(name)
                .customerPhone(phone)
                .bookingTime(LocalDateTime.now())
                .status(BookingStatus.BOOKED)
                .build();
        bookingRepository.save(booking);
    }

    public List<Booking> getBookingsByPhone(String phone) {
        return bookingRepository.findByCustomerPhone(phone);
    }

    public void cancelBooking(Long id) {
        Booking booking = bookingRepository.findById(id).orElse(null);
        if (booking != null) {
            booking.setStatus(BookingStatus.CANCELLED);
            bookingRepository.save(booking);
        }
    }
}