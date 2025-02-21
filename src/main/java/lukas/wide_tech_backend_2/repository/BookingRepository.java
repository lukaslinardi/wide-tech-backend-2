package lukas.wide_tech_backend_2.repository;

import lukas.wide_tech_backend_2.entity.Bookings;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Long> {
    Page<Bookings> findAll(Pageable pageable);
}
