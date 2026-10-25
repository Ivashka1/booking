package app;

import java.util.List;

public interface BookingRepository {
    Booking save(Booking booking); //returns with id

    List<Booking> findAll();
}
