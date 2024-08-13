package com.app.service;

import com.app.dto.BookingDto;
import com.app.entity.Booking;
import com.app.entity.Status;
import com.app.entity.User;
import com.app.exception.ResourceNotFoundException;
import com.app.repository.BookingRepository;
import com.app.repository.ServiceRepository;
import com.app.repository.UserRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookingService {
    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private ServiceRepository serviceRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public List<BookingDto> getAllBookings() {
        return bookingRepository.findAll().stream()
                .map(booking -> modelMapper.map(booking, BookingDto.class))
                .collect(Collectors.toList());
    }

    public BookingDto getBookingById(Long bookingId) {
        return modelMapper.map(bookingRepository.findById(bookingId).orElse(null), BookingDto.class);
    }

    public BookingDto createBooking(BookingDto bookingDto) {
        // Fetch the user and service entities
        User user = userRepository.findById(bookingDto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        com.app.entity.Service service = serviceRepository.findById(bookingDto.getServiceId())
                .orElseThrow(() -> new ResourceNotFoundException("Service not found"));

        // Map DTO to entity and set the user, service, and date
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setService(service);
        booking.setDate(LocalDateTime.now()); // Set the date
        booking.setStatus(Status.PENDING); // Default status

        // Save and return the booking
        Booking savedBooking = bookingRepository.save(booking);
        return modelMapper.map(savedBooking, BookingDto.class);
    }

    public BookingDto updateBooking(Long bookingId, BookingDto bookingDto) {
        Booking booking = bookingRepository.findById(bookingId).orElse(null);
        if (booking != null) {
            modelMapper.map(bookingDto, booking);
            booking = bookingRepository.save(booking);
            return modelMapper.map(booking, BookingDto.class);
        }
        return null;
    }

    public void deleteBooking(Long bookingId) {
        bookingRepository.deleteById(bookingId);
    }
}
