package com.app.dto;

import java.time.LocalDateTime;

import com.app.entity.Status;

public class BookingDto {
    private Long bookingId;
   private Long userId;
   private Long serviceId;
   private LocalDateTime bookingDate; 
   // private Long userId;
    //private Long serviceId;
    //private LocalDateTime bookingDate;
    private Status status;
	public Long getBookingId() {
		return bookingId;
	}
	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getServiceId() {
		return serviceId;
	}
	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}
	public LocalDateTime getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}

    // Getters and Setters
   
}
