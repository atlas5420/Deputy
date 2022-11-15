package com.deputy.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import org.springframework.lang.Nullable;

import com.deputy.dto.EmployeePrivateDto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *유저의 개인 신상 정보를 저장하는 모델입니다.
 *회사 내부 정보를 EmployeePublic class model과 OneToOne Mapping 하여 관리합니다.
 */
@Getter
@NoArgsConstructor
@Entity(name = "employee_private")
public class EmployeePrivate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//아이디
	@NotBlank(message = "아이디가 입력되지 않았습니다.")
	@Column(unique = true)
	private String username;
	
	//비밀번호
	@NotBlank(message = "비밀번호가 입력되지 않았습니다.")
	private String password;
	
	//실명
	@NotBlank(message = "성명이 입력되지 않았습니다.")
	private String name;
	
	//우편번호
	@NotBlank(message = "주소가 입력되지 않았습니다.")
	private String postCode;
	
	//주소
	@NotBlank(message = "주소가 입력되지 않았습니다.")
	private String address;
	
	//상세주소
	@Nullable
	private String detailAddress;
	
	//참고항목
	@Nullable
	private String extraAddress;
	
	//전화번호
	@NotBlank(message = "전화번호가 입력되지 않았습니다.")
	private String mobile;
	
	//메일주소
	@NotBlank(message = "이메일이 입력되지 않았습니다.")
	private String email;
	
	//회사 내부 정보
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee_public")
	private EmployeePublic employeePublic;
	
	@Builder(builderClassName = "joinBuilder" ,builderMethodName = "joinBuilder")
	public EmployeePrivate(EmployeePrivateDto dto) {
		this.id = dto.getId();
		this.username = dto.getUsername();
		this.password = dto.getPassword();
		this.name = dto.getName();
		this.postCode = dto.getPostCode();
		this.address = dto.getAddress();
		this.detailAddress = dto.getDetailAddress();
		this.extraAddress = dto.getExtraAddress();
		this.mobile = dto.getMobile();
		this.email = dto.getEmail();
		this.employeePublic = dto.getEmployeePublic();
	}
}
