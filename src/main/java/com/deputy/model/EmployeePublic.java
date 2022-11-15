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
 * ������ ȸ�� ���� ������ �����ϴ� ���Դϴ�.
 * department(�μ�) position(��å)�� ENUM class�� Ȱ���Ͽ� �ۼ��˴ϴ�. 
 * resign(��翩��)�� boolean Ÿ������ �����˴ϴ�.
 * ����� ������ WorkHistory class Model�� ����ϸ� OneToMany Mapping �Ͽ� ������ �����մϴ�.
 * ���Ѱ���(ADMIN, MANAGER, EMPLOYEE)�� Role class Model�� ����Ͽ� ManyToOne Mapping ����˴ϴ�.
 */
@Getter
@NoArgsConstructor
@Entity(name = "employee_public")
public class EmployeePublic {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//�����ȣ
	@NotBlank
	private String number;
	
	//�μ�
	@Enumerated(EnumType.STRING)
	private Department department;
	
	//��å
	@Enumerated(EnumType.STRING)
	private Position position;
	
	//��翩��
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private boolean resign;
	
	//����� ���� ����
	@OneToMany(mappedBy = "employeePublic", cascade = CascadeType.ALL)
	@ToString.Exclude 
	private List<WorkHistory> histories;
	
	//���Ѽ���
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
