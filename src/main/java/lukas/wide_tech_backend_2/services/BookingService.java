package lukas.wide_tech_backend_2.services;

import lukas.wide_tech_backend_2.entity.Bookings;
import lukas.wide_tech_backend_2.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Page<Bookings> getBookings(int offset, int limit) {
        return bookingRepository.findAll(PageRequest.of(offset - 1, limit));
    }

    ;

    public void createBookings(List<Bookings> carts) {
        bookingRepository.saveAll(carts);
    }

    ;

    public void deleteBooking(Long id) {
        bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Booking with ID " + id + " not found"));
        bookingRepository.deleteById(id);

    }

    ;

    public void updateBooking(Long id, Bookings updatedBooking) {
        Bookings existingBooking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Booking with ID " + id + " not found"));

        existingBooking.setName(updatedBooking.getName());
        existingBooking.setMessage(updatedBooking.getMessage());
        existingBooking.setTotal(updatedBooking.getTotal());

        bookingRepository.save(existingBooking);
    }
}