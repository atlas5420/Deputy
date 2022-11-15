package com.deputy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deputy.model.EmployeePublic;
import com.deputy.model.WorkHistory;

public interface WorkHistoryRepository extends JpaRepository<WorkHistory, Long>{
	List<WorkHistory> findByEmployeePublic(EmployeePublic employeePublic);
}
