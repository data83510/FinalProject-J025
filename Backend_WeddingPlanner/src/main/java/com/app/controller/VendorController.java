package com.app.controller;

import com.app.dto.BookingDto;
import com.app.dto.VendorDto;
import com.app.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RestController
@RequestMapping("/api/vendors")
public class VendorController {
    @Autowired
    private VendorService vendorService;

    @GetMapping
    public List<VendorDto> getAllVendors() {
        return vendorService.getAllVendors();
    }
    //booking status
    @GetMapping("/{vendorId}/bookings/pending")
    public ResponseEntity<List<BookingDto>> getPendingBookings(@PathVariable Long vendorId) {
        List<BookingDto> bookings = vendorService.getPendingBookings(vendorId);
        return ResponseEntity.ok(bookings);
    }

    @PostMapping("/{vendorId}/bookings/{bookingId}/accept")
    public ResponseEntity<BookingDto> acceptBooking(@PathVariable Long vendorId, @PathVariable Long bookingId) throws AccessDeniedException {
        BookingDto booking = vendorService.acceptBooking(vendorId, bookingId);
        return ResponseEntity.ok(booking);
    }

    @PostMapping("/{vendorId}/bookings/{bookingId}/reject")
    public ResponseEntity<BookingDto> rejectBooking(@PathVariable Long vendorId, @PathVariable Long bookingId) throws AccessDeniedException {
        BookingDto booking = vendorService.rejectBooking(vendorId, bookingId);
        return ResponseEntity.ok(booking);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendorDto> getVendorById(@PathVariable Long id) {
        VendorDto vendorDto = vendorService.getVendorById(id);
        if (vendorDto != null) {
            return ResponseEntity.ok(vendorDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public VendorDto createVendor(@RequestBody VendorDto vendorDto) {
        return vendorService.createVendor(vendorDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<VendorDto> updateVendor(@PathVariable Long id, @RequestBody VendorDto vendorDto) {
        VendorDto updatedVendor = vendorService.updateVendor(id, vendorDto);
        if (updatedVendor != null) {
            return ResponseEntity.ok(updatedVendor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVendor(@PathVariable Long id) {
        vendorService.deleteVendor(id);
        return ResponseEntity.noContent().build();
    }
}
