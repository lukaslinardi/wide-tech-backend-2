package lukas.wide_tech_backend_2.services;

import lukas.wide_tech_backend_2.entity.Bookings;
import lukas.wide_tech_backend_2.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    public Page<Bookings> getBookings(int page, int size) {
        return bookingRepository.findAll(PageRequest.of(page, size));
    };

    public void createBookings(List<Bookings> carts) {
        bookingRepository.saveAll(carts);
    };

    public void deleteBooking(Long id) {
        bookingRepository.deleteById(id);
    };

    public Optional<Bookings> updateBooking(Long id, Bookings updatedBooking) {
        return bookingRepository.findById(id).map(existingBooking -> {
            existingBooking.setName(updatedBooking.getName());
            existingBooking.setMessage(updatedBooking.getMessage());
            existingBooking.setTotal(updatedBooking.getTotal());
            return bookingRepository.save(existingBooking);
        });
    }
}
