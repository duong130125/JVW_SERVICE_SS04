package ra.session_04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.session_04.entity.Flight;
import ra.session_04.service.BookingService;
import ra.session_04.service.FlightService;

@Controller
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private FlightService flightService;

    @Autowired
    private BookingService bookingService;

    @GetMapping("/new/{flightId}")
    public String showBookingForm(@PathVariable Long flightId, Model model) {
        Flight flight = flightService.getFlightById(flightId);
        model.addAttribute("flight", flight);
        return "booking-form";
    }

    @PostMapping("/book")
    public String bookTicket(@RequestParam Long flightId,
                             @RequestParam String name,
                             @RequestParam String phone) {
        Flight flight = flightService.getFlightById(flightId);
        bookingService.bookFlight(flight, name, phone);
        return "redirect:/bookings/list?phone=" + phone;
    }

    @GetMapping("/list")
    public String listBookings(@RequestParam String phone, Model model) {
        model.addAttribute("bookings", bookingService.getBookingsByPhone(phone));
        return "bookings";
    }

    @GetMapping("/cancel/{id}")
    public String cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
        return "redirect:/flights";
    }
}