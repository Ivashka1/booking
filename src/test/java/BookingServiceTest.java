import app.Booking;
import app.BookingRepository;
import app.BookingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)          // вмикає @Mock і @InjectMocks
class BookingServiceTest {

    @Mock
    BookingRepository repository;
    @InjectMocks
    BookingService service;     // мок підставиться через конструктор

    @Test
    @DisplayName("створює бронювання і зберігає його")
    void create_validInterval_savesBooking() {
        // Arrange
        var start = LocalDateTime.of(2026, 11, 1, 10, 0);
        var end = start.plusHours(2);
        when(repository.save(any())).thenAnswer(inv -> inv.getArgument(0));

        // Act
        var result = service.create("Room A", start, end);

        // Assert
        assertThat(result.resourceName()).isEqualTo("Room A");
        verify(repository).save(any(Booking.class));
    }

    @Test
    void create_endBeforeStart_throws() {
        var start = LocalDateTime.of(2026, 11, 1, 10, 0);

        assertThatThrownBy(() -> service.create("Room A", start, start.minusHours(1)))
                .isInstanceOf(IllegalArgumentException.class);

        verifyNoInteractions(repository);    // нічого не збережено
    }
}
