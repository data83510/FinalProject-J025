package com.app.controller;

import com.app.dto.ServiceDto;
import com.app.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    @GetMapping
    public List<ServiceDto> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDto> getServiceById(@PathVariable Long id) {
        ServiceDto serviceDto = serviceService.getServiceById(id);
        if (serviceDto != null) {
            return ResponseEntity.ok(serviceDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
  //  @GetMapping("/{type}")
    //public List<ServiceDto> getServicesByType(@PathVariable String type) {
      //  return serviceService.getServicesByType(type);
    //}

   // @PostMapping
    //public ServiceDto createService(@RequestBody ServiceDto serviceDto) {
      //  return serviceService.createService(serviceDto);
   // }
    @PostMapping
    public ServiceDto addService(@RequestBody ServiceDto serviceDto) {
        return serviceService.saveService(serviceDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDto> updateService(@PathVariable Long id, @RequestBody ServiceDto serviceDto) {
        ServiceDto updatedService = serviceService.updateService(id, serviceDto);
        if (updatedService != null) {
            return ResponseEntity.ok(updatedService);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
}
