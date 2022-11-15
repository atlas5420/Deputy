package com.deputy.config;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.deputy.model.EmployeePrivate;
import com.deputy.repository.EmployeePrivateRepository;

@Service
@Transactional
public class PrincipalDetailService implements UserDetailsService {

	@Autowired
	private EmployeePrivateRepository employeePrivateRepository;
	
	// 시큐리티 session 내부(Authentication 내부(Userdetails))
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		EmployeePrivate employeeEntity = employeePrivateRepository.findByUsername(username);
		if(employeeEntity != null) {
			return new PrincipalDetail(employeeEntity);
			}
		return null;
	}
	
	
}
