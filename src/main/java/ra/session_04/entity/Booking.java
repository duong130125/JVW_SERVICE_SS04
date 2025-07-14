package ra.session_04.entity;

import jakarta.persistence.*;
import lombok.*;
import ra.session_04.BookingStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "flight_id", nullable = false)
    private Flight flight;

    @Column(nullable = false, length = 100)
    private String customerName;
    @Column(nullable = false, length = 15)
    private String customerPhone;
    @Column(nullable = false, length = 100)
    private LocalDateTime bookingTime;

    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}