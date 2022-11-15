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
 *������ ���� �Ż� ������ �����ϴ� ���Դϴ�.
 *ȸ�� ���� ������ EmployeePublic class model�� OneToOne Mapping �Ͽ� �����մϴ�.
 */
@Getter
@NoArgsConstructor
@Entity(name = "employee_private")
public class EmployeePrivate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//���̵�
	@NotBlank(message = "���̵� �Էµ��� �ʾҽ��ϴ�.")
	@Column(unique = true)
	private String username;
	
	//��й�ȣ
	@NotBlank(message = "��й�ȣ�� �Էµ��� �ʾҽ��ϴ�.")
	private String password;
	
	//�Ǹ�
	@NotBlank(message = "������ �Էµ��� �ʾҽ��ϴ�.")
	private String name;
	
	//�����ȣ
	@NotBlank(message = "�ּҰ� �Էµ��� �ʾҽ��ϴ�.")
	private String postCode;
	
	//�ּ�
	@NotBlank(message = "�ּҰ� �Էµ��� �ʾҽ��ϴ�.")
	private String address;
	
	//���ּ�
	@Nullable
	private String detailAddress;
	
	//�����׸�
	@Nullable
	private String extraAddress;
	
	//��ȭ��ȣ
	@NotBlank(message = "��ȭ��ȣ�� �Էµ��� �ʾҽ��ϴ�.")
	private String mobile;
	
	//�����ּ�
	@NotBlank(message = "�̸����� �Էµ��� �ʾҽ��ϴ�.")
	private String email;
	
	//ȸ�� ���� ����
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
