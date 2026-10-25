package app;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        BookingService bs = new BookingService(new InMemoryBookingRepository());

        var start = LocalDateTime.of(2026, 11, 1, 10, 0);
        var end = start.plusDays(2);
        bs.create("Sea side", start, end);

        var start2 = start.plusDays(2);
        var end2 = start2.plusDays(3);
        bs.create("Sea side", start2, end2);

        bs.findAll().forEach(System.out::println);
    }
}
