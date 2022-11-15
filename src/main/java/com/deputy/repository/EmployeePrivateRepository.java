package com.deputy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deputy.model.EmployeePrivate;

public interface EmployeePrivateRepository extends JpaRepository<EmployeePrivate, Long> {
	public EmployeePrivate findByUsername(String username);
	List<EmployeePrivate> findByNameContaining(String name);
}
