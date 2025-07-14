package ra.session_04.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String flightNumber;
    @Column(nullable = false)
    private String departure;
    @Column(nullable = false)
    private String destination;
    @Column(nullable = false)
    private double price;
}