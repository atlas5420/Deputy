package com.deputy.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.lang.Nullable;

import com.deputy.model.EmployeePublic;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeePrivateDto {
	
	private Long id;
	
	@NotBlank(message = "���̵�� �ʼ� �Է� ���Դϴ�.")
	@ApiModelProperty(value = "���̵�", dataType = "String", required = true, example = "test")
	private String username;
	
	@NotBlank(message = "��й�ȣ�� �ʼ� �Է� ���Դϴ�.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "��й�ȣ�� 8~16�� ���� �� �ҹ���, ����, Ư�����ڸ� ����ϼ���.")
	@ApiModelProperty(value = "��й�ȣ", dataType = "String", required = true, example = "1q2w3e!@#")
	private String password;
	
	@NotBlank(message = "������ �ʼ� �Է� ���Դϴ�.")
	@Pattern(regexp = "^[��-����-�R]*$", message = "�ѱ۷� �Է����ּ���.")
	@ApiModelProperty(value = "�Ǹ�", dataType = "String", required = true, example = "����")
	private String name;
	
	@NotBlank(message = "�ּҴ� �ʼ� �Է� ���Դϴ�.")
	@ApiModelProperty(value = "�����ȣ", dataType = "String", required = true, example = "���� open api�� ���� �Է�")
	private String postCode;
	
	@NotBlank(message = "�ּҴ� �ʼ� �Է� ���Դϴ�.")
	@ApiModelProperty(value = "�ּ�", dataType = "String", required = true, example = "���� open api�� ���� �Է�")
	private String address;
	
	@Nullable
	@ApiModelProperty(value = "���ּ�", dataType = "String", required = true, example = "���� open api�� ���� �Է�(101�� 101ȣ)")
	private String detailAddress;
	
	@Nullable
	@ApiModelProperty(value = "�����׸�", dataType = "String", required = true, example = "���� open api�� ���� �Է�(���ǵ���)")
	private String extraAddress;
	
	//����
	@NotBlank(message = "�޴��ȣ�� �ʼ� �Է� ���Դϴ�.")
	@Pattern(regexp = "^01(?:0|1[6-9])(\\d{3,4})(\\d{4})", message = "�ѱ۷� �Է����ּ���.")
	@ApiModelProperty(value = "�޴��ȣ", dataType = "String", required = true, example = "01012341234")
	private String mobile;
	
	@NotBlank(message = "�̸����� �ʼ� �Է� ���Դϴ�.")
	@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "�̸��� ������ �ùٸ��� �ʽ��ϴ�.")
	@ApiModelProperty(value = "�̸���", dataType = "String", required = true, example = "tset@naver.com")
	private String email;
	
	private EmployeePublic employeePublic;

}