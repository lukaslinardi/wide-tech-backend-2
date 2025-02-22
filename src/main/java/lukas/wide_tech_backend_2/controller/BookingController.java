package lukas.wide_tech_backend_2.controller;

import lukas.wide_tech_backend_2.dto.CustomResponse;
import lukas.wide_tech_backend_2.entity.Bookings;
import lukas.wide_tech_backend_2.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookings")
public class BookingController {
        @Autowired
        private BookingService bookingService;

        @GetMapping
        public ResponseEntity<?> getBookings(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
            CustomResponse customResponse = new CustomResponse();
            Map<String, String> message = new HashMap<>();

            try{
                message.put("en", "Success");
                message.put("id", "Sukses");
                Page<Bookings> res = bookingService.getBookings(page, size);
                customResponse.CustomResponse(HttpStatus.OK, message, res);
                return ResponseEntity.ok(customResponse);
            } catch (Exception e) {
                message.put("en", "Failed to get data");
                message.put("id", "Gagal mengambil data");
                customResponse.CustomResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
            }
        };

        @PostMapping
        public ResponseEntity<?> createBookings(@RequestBody List<Bookings> newBookings) {
            CustomResponse customResponse = new CustomResponse();
            Map<String, String> message = new HashMap<>();

            try{
                message.put("en", "Success");
                message.put("id", "Sukses");
                bookingService.createBookings(newBookings);
                customResponse.CustomResponse(HttpStatus.OK, message, null);
                return ResponseEntity.ok(customResponse);
            } catch (Exception e) {
                message.put("en", "Failed to create data");
                message.put("id", "Gagal membuat data");
                customResponse.CustomResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
            }

        };

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
            CustomResponse customResponse = new CustomResponse();
            Map<String, String> message = new HashMap<>();

            try{
                message.put("en", "Success");
                message.put("id", "Sukses");
                bookingService.deleteBooking(id);
                customResponse.CustomResponse(HttpStatus.OK, message, null);
                return ResponseEntity.ok(customResponse);
            } catch (Exception e) {
                message.put("en", "Failed to delete data");
                message.put("id", "Gagal delete data");
                customResponse.CustomResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
            }

        };

        @PutMapping("/{id}")
        public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody Bookings bookings) {
            CustomResponse customResponse = new CustomResponse();
            Map<String, String> message = new HashMap<>();

            try{
                message.put("en", "Success");
                message.put("id", "Sukses");
                bookingService.updateBooking(id, bookings);
                customResponse.CustomResponse(HttpStatus.OK, message, null);
                return ResponseEntity.ok(customResponse);
            } catch (Exception e) {
                message.put("en", "Failed to delete data");
                message.put("id", "Gagal delete data");
                customResponse.CustomResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
            }
    };
}
