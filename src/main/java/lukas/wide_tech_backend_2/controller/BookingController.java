package lukas.wide_tech_backend_2.controller;

import lukas.wide_tech_backend_2.dto.CustomResponse;
import lukas.wide_tech_backend_2.entity.Bookings;
import lukas.wide_tech_backend_2.services.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/bookings")
public class BookingController {
    private static final Logger log = LoggerFactory.getLogger(BookingController.class);
    @Autowired
    private BookingService bookingService;

    @GetMapping
    public ResponseEntity<?> getBookings(@RequestParam(defaultValue = "0") int offset, @RequestParam(defaultValue = "0") int limit) {
        CustomResponse customResponse = new CustomResponse();
        Map<String, String> message = new HashMap<>();

        if (offset == 0 || limit == 0) {
            message.put("status", "Failed to get data");
            message.put("reason", "limit or offset is 0");
            customResponse.CustomResponse(HttpStatus.BAD_REQUEST, message, null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(customResponse);
        }

        try {
            message.put("status", "Success");
            Page<Bookings> res = bookingService.getBookings(offset, limit);
            customResponse.CustomResponse(HttpStatus.OK, message, res);
            return ResponseEntity.ok(customResponse);
        } catch (Exception e) {
            log.error("error from booking controller" + e);
            message.put("en", "Failed to get data");
            message.put("id", "Gagal mengambil data");
            customResponse.CustomResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
        }
    }

    ;

    @PostMapping
    public ResponseEntity<?> createBookings(@RequestBody List<Bookings> newBookings) {
        CustomResponse customResponse = new CustomResponse();
        Map<String, String> message = new HashMap<>();

        try {
            message.put("en", "Success");
            message.put("id", "Sukses");
            bookingService.createBookings(newBookings);
            customResponse.CustomResponse(HttpStatus.OK, message, null);
            return ResponseEntity.ok(customResponse);
        } catch (ResponseStatusException ex) {
            log.error("error from booking controller" + ex);
            message.put("status", "Failed to create data");
            message.put("reason", ex.getReason());
            customResponse.CustomResponse(ex.getStatusCode(), message, null);
            return ResponseEntity.status(ex.getStatusCode()).body(customResponse);
        } catch (Exception err) {
            log.error("error from booking controller" + err);
            message.put("en", "Failed to create data");
            customResponse.CustomResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
        }

    }

    ;

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable Long id) {
        CustomResponse customResponse = new CustomResponse();
        Map<String, String> message = new HashMap<>();

        try {
            message.put("status", "Success");
            bookingService.deleteBooking(id);
            customResponse.CustomResponse(HttpStatus.OK, message, null);
            return ResponseEntity.ok(customResponse);
        } catch (ResponseStatusException ex) {
            log.error("error from booking controller" + ex);
            message.put("status", "Failed to delete data");
            message.put("reason", ex.getReason());
            customResponse.CustomResponse(ex.getStatusCode(), message, null);
            return ResponseEntity.status(ex.getStatusCode()).body(customResponse);
        } catch (Exception err) {
            log.error("error from booking controller" + err);
            message.put("status", "Failed to delete data");
            message.put("reason", err.getMessage());
            customResponse.CustomResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
        }

    }

    ;

    @PutMapping("/{id}")
    public ResponseEntity<?> updateBooking(@PathVariable Long id, @RequestBody Bookings bookings) {
        CustomResponse customResponse = new CustomResponse();
        Map<String, String> message = new HashMap<>();

        try {
            message.put("status", "Success");
            bookingService.updateBooking(id, bookings);
            customResponse.CustomResponse(HttpStatus.OK, message, null);
            return ResponseEntity.ok(customResponse);
        } catch (ResponseStatusException ex) {
            log.error("error from booking controller" + ex);
            message.put("status", "Failed to update data");
            message.put("reason", ex.getReason());
            customResponse.CustomResponse(ex.getStatusCode(), message, null);
            return ResponseEntity.status(ex.getStatusCode()).body(customResponse);
        } catch (Exception err) {
            log.error("error from booking controller" + err);
            message.put("en", "Failed to update data");
            message.put("reason", err.getMessage());
            customResponse.CustomResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(customResponse);
        }
    }

    ;
}
