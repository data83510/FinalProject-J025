package com.app.repository;

import com.app.entity.Booking;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookingRepository extends JpaRepository<Booking, Long> {

	@Query("SELECT b FROM Booking b WHERE b.service.vendor.vendorId = :vendorId AND b.status = :status")
    List<Booking> findByVendorIdAndStatus(@Param("vendorId") Long vendorId, @Param("status") String status);
}
