package com.app.service;

import com.app.dto.BookingDto;
import com.app.dto.VendorDto;
import com.app.entity.Booking;
import com.app.entity.Status;
import com.app.entity.Vendor;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.BookingRepository;
import com.app.repository.VendorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VendorService {
	 @Autowired
	    private BookingRepository bookingRepository;
	
    @Autowired
    private VendorRepository vendorRepository;

    @Autowired
    private ModelMapper modelMapper;
 // Method to get pending bookings for a specific vendor
    public List<BookingDto> getPendingBookings(Long vendorId) {
        List<Booking> bookings = bookingRepository.findByVendorIdAndStatus(vendorId, "PENDING");
        return bookings.stream()
            .map(booking -> modelMapper.map(booking, BookingDto.class))
            .collect(Collectors.toList());
    }
    // Method to accept a booking
    public BookingDto acceptBooking(Long vendorId, Long bookingId) throws AccessDeniedException {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        
        if (!booking.getService().getVendor().getVendorId().equals(vendorId)) {
            throw new AccessDeniedException("This booking does not belong to the vendor");
        }

        booking.setStatus(Status.ACCEPTED);
        Booking updatedBooking = bookingRepository.save(booking);
        return modelMapper.map(updatedBooking, BookingDto.class);
    }

    // Method to reject a booking
    public BookingDto rejectBooking(Long vendorId, Long bookingId) throws AccessDeniedException {
        Booking booking = bookingRepository.findById(bookingId)
            .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
        
        if (!booking.getService().getVendor().getVendorId().equals(vendorId)) {
            throw new AccessDeniedException("This booking does not belong to the vendor");
        }

        booking.setStatus(Status.REJECTED);
        Booking updatedBooking = bookingRepository.save(booking);
        return modelMapper.map(updatedBooking, BookingDto.class);
    }
    public List<VendorDto> getAllVendors() {
        return vendorRepository.findAll().stream()
                .map(vendor -> modelMapper.map(vendor, VendorDto.class))
                .collect(Collectors.toList());
    }

    public VendorDto getVendorById(Long vendorId) {
        return modelMapper.map(vendorRepository.findById(vendorId).orElse(null), VendorDto.class);
    }

    public VendorDto createVendor(VendorDto vendorDto) {
        Vendor vendor = modelMapper.map(vendorDto, Vendor.class);
        vendor = vendorRepository.save(vendor);
        return modelMapper.map(vendor, VendorDto.class);
    }

    public VendorDto updateVendor(Long vendorId, VendorDto vendorDto) {
        Vendor vendor = vendorRepository.findById(vendorId).orElse(null);
        if (vendor != null) {
            modelMapper.map(vendorDto, vendor);
            vendor = vendorRepository.save(vendor);
            return modelMapper.map(vendor, VendorDto.class);
        }
        return null;
    }

    public void deleteVendor(Long vendorId) {
        vendorRepository.deleteById(vendorId);
    }
}
