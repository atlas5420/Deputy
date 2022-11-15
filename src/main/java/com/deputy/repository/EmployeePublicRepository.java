package com.deputy.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deputy.model.EmployeePublic;

public interface EmployeePublicRepository extends JpaRepository<EmployeePublic, Long> {
	EmployeePublic findByNumber(String number);
}
