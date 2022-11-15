package com.deputy.config;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.deputy.model.EmployeePrivate;
import com.deputy.model.Role;

import lombok.Getter;

@Getter
public class PrincipalDetail implements UserDetails {

	private EmployeePrivate employeePrivate;
	private Role role;
	public PrincipalDetail(EmployeePrivate employeePrivate) {
		this.employeePrivate = employeePrivate;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+employeePrivate.getEmployeePublic().getRole().getRoleName()));
		System.out.println(authorities);
		return authorities;
	}

	@Override
	public String getPassword() {
		return employeePrivate.getPassword();
	}

	@Override
	public String getUsername() {
		return employeePrivate.getUsername();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	
}
