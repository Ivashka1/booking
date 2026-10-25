package app;

import java.time.LocalDateTime;

public record Booking(Long id, String resourceName, LocalDateTime start, LocalDateTime end) {
}
