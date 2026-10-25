package app;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class InMemoryBookingRepository implements BookingRepository {
    private final List<Booking> bookings = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    @Override
    public Booking save(Booking booking) {
        Booking newBooking = new Booking(idGenerator.getAndIncrement(), booking.resourceName(), booking.start(), booking.end());
        bookings.add(newBooking);
        return newBooking;
    }

    @Override
    public List<Booking> findAll() {
        return List.copyOf(bookings);
    }
}
