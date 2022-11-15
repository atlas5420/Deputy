package com.deputy.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deputy.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findByRoleName(String roleName);
}
