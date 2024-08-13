package com.app;

import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.app.dto.BookingDto;
import com.app.dto.PaymentDto;
import com.app.entity.Booking;
import com.app.entity.Payment;
import com.app.entity.PaymentMethod;
import com.app.entity.Status;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean // equivalent to <bean id ..../> in xml file
	public ModelMapper mapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT)
				.setPropertyCondition(Conditions.isNotNull());
		
		// Custom mapping for vendorId
				modelMapper.typeMap(com.app.entity.Service.class, com.app.dto.ServiceDto.class)
						.addMappings(mapper -> mapper.map(src -> src.getVendor().getVendorId(), com.app.dto.ServiceDto::setVendorId));
				// Custom mapping for ServiceDto
			    modelMapper.typeMap(com.app.entity.Service.class, com.app.dto.ServiceDto.class)
			            .addMappings(mapper -> mapper.map(src -> src.getVendor().getVendorId(), com.app.dto.ServiceDto::setVendorId));
			   // Custom mapping for bookingDto
			    modelMapper.typeMap(Booking.class, BookingDto.class).addMappings(mapper -> {
			        mapper.map(src -> src.getUser().getUserId(), BookingDto::setUserId);
			        mapper.map(src -> src.getService().getServiceId(), BookingDto::setServiceId);
			        mapper.map(Booking::getDate, BookingDto::setBookingDate);
			    });
			    modelMapper.typeMap(Payment.class, PaymentDto.class).addMappings(mapper -> {
			        mapper.map(Payment::getPaymentMethod, PaymentDto::setPaymentMethod);
			        mapper.map(src -> src.getBooking().getBookingId(), PaymentDto::setBookingId);
			    });
			
				return modelMapper;
				
				
		
	}
	


}
