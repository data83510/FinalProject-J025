package com.app.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Vendor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long vendorId;
    private String vendorName;
    @Enumerated(EnumType.STRING)
    private VendorType vendorType;
    private String description;
    private double rating;

    @OneToMany(mappedBy = "vendor")
    private Set<Service> services;

    @OneToMany(mappedBy = "vendor")
    private Set<Review> reviews;

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public Set<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Set<Review> reviews) {
        this.reviews = reviews;
    }
}
