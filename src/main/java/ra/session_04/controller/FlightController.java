package ra.session_04.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.session_04.entity.Flight;
import ra.session_04.service.FlightService;

@Controller
    @RequestMapping("/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @GetMapping
    public String showFlights(Model model,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "") String departure,
                              @RequestParam(defaultValue = "") String destination) {
        Page<Flight> flights = flightService.getFlights(departure, destination, page);
        model.addAttribute("flights", flights);
        model.addAttribute("departure", departure);
        model.addAttribute("destination", destination);
        return "flights";
    }
}