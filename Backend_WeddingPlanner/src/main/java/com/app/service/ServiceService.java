package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.app.dto.ServiceDto;
import com.app.entity.Service;
import com.app.entity.Vendor;
import com.app.repository.ServiceRepository;
import com.app.repository.VendorRepository;

@org.springframework.stereotype.Service

public class ServiceService {
    @Autowired
    private ServiceRepository serviceRepository;

    @Autowired
    private VendorRepository vendorRepository;
    
    @Autowired
    private ModelMapper modelMapper;

    public List<ServiceDto> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(service -> modelMapper.map(service, ServiceDto.class))
                .collect(Collectors.toList());
    }

    public ServiceDto getServiceById(Long serviceId) {
        return modelMapper.map(serviceRepository.findById(serviceId).orElse(null), ServiceDto.class);
    }
    public List<ServiceDto> getServicesByType(String type) {
        List<Service> services = serviceRepository.findByType(type);
        return services.stream()
                       .map(service -> modelMapper.map(service, ServiceDto.class))
                       .collect(Collectors.toList());
    }
    public ServiceDto saveService(ServiceDto serviceDto) {
        Vendor vendor = vendorRepository.findById(serviceDto.getVendorId())
                                        .orElseThrow(() -> new RuntimeException("Vendor not found"));

        if (!serviceDto.getType().equalsIgnoreCase(vendor.getType().name())) {
            throw new RuntimeException("Service type does not match Vendor type.");
        }
        
        Service service = modelMapper.map(serviceDto, Service.class);
        service.setVendor(vendor);

        Service savedService = serviceRepository.save(service);
        return modelMapper.map(savedService, ServiceDto.class);
    }
    
    public ServiceDto createService(ServiceDto serviceDto) {
        Service service = modelMapper.map(serviceDto, Service.class);
        service = serviceRepository.save(service);
        return modelMapper.map(service, ServiceDto.class);
    }

    public ServiceDto updateService(Long serviceId, ServiceDto serviceDto) {
        Service service = serviceRepository.findById(serviceId).orElse(null);
        if (service != null) {
            modelMapper.map(serviceDto, service);
            service = serviceRepository.save(service);
            return modelMapper.map(service, ServiceDto.class);
        }
        return null;
    }

    public void deleteService(Long serviceId) {
        serviceRepository.deleteById(serviceId);
    }
}
