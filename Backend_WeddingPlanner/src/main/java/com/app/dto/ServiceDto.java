package com.app.dto;

public class ServiceDto {
	 private Long serviceId;
	    private String name;
	    private String type;
	    private double price;
	    private Long vendorId;
    // Getters and Setters
		public Long getServiceId() {
			return serviceId;
		}
		public void setServiceId(Long serviceId) {
			this.serviceId = serviceId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getType() {
			return type;
		}
		public void setType(String type) {
			this.type = type;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public Long getVendorId() {
			return vendorId;
		}
		public void setVendorId(Long vendorId) {
			this.vendorId = vendorId;
		}
    
}
