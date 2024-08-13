package com.app.dto;

import com.app.entity.VendorType;

public class VendorDto {
    private Long vendorId;
    private String vendorName;
    private VendorType vendorType;
    private String description;

    // Getters and Setters
    public Long getVendorId() {
        return vendorId;
    }

    public void setVendorId(Long vendorId) {
        this.vendorId = vendorId;
    }
    public String getName() {
        return vendorName;
    }

    public void setName(String name) {
        this.vendorName = name;
    }

    public VendorType getType() {
        return vendorType;
    }

    public void setType(VendorType type) {
        this.vendorType = type;
    }
   
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
