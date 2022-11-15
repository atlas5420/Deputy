package com.deputy.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.deputy.dto.EmployeePublicDto;
import com.deputy.model.employeeEnum.Department;
import com.deputy.model.employeeEnum.Position;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


/**
 * 유저의 회사 내부 정보를 저장하는 모델입니다.
 * department(부서) position(직책)은 ENUM class를 활용하여 작성됩니다. 
 * resign(퇴사여부)는 boolean 타입으로 관리됩니다.
 * 출퇴근 정보는 WorkHistory class Model을 사용하며 OneToMany Mapping 하여 정보를 관리합니다.
 * 권한관리(ADMIN, MANAGER, EMPLOYEE)는 Role class Model을 사용하여 ManyToOne Mapping 연결됩니다.
 */
@Getter
@NoArgsConstructor
@Entity(name = "employee_public")
public class EmployeePublic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//사원번호
	@NotBlank
	private String number;
	
	//부서
	@Enumerated(EnumType.STRING)
	private Department department;
	
	//직책
	@Enumerated(EnumType.STRING)
	private Position position;
	
	//퇴사여부
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private boolean resign;
	
	//출퇴근 정보 관리
	@OneToMany(mappedBy = "employeePublic", cascade = CascadeType.ALL)
	@ToString.Exclude 
	private List<WorkHistory> histories;
	
	//권한설정
	@ManyToOne
	private Role role;
	
	@Builder(builderClassName = "joinBuilder", builderMethodName = "joinBuilder")
	public EmployeePublic(EmployeePublicDto dto) {
		this.id = dto.getId();
		this.number = dto.getNumber();
		this.department = dto.getDepartment();
		this.position = dto.getPosition();
		this.resign = dto.isResign();
		this.histories = dto.getHistories();
		this.role = dto.getRole();
	}
}
