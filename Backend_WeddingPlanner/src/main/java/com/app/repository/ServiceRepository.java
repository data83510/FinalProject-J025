package com.app.repository;

import com.app.entity.Service;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {

	List<Service> findByType(String type);
}
