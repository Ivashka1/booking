package app;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class BookingService {
    private final BookingRepository repository;

    public BookingService(BookingRepository repository) {
        this.repository = repository;
    }

    public Booking create(String resourceName, LocalDateTime start, LocalDateTime end) {
        if (start == null || end == null || !end.isAfter(start)) {
            throw new IllegalArgumentException("End date must be after start date");
        }
        return repository.save(new Booking(null, resourceName, start, end));
    }

    public List<Booking> findAll() {
        return repository.findAll();
    }
}
