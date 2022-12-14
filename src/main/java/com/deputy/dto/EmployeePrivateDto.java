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
	
	@NotBlank(message = "아이디는 필수 입력 값입니다.")
	@ApiModelProperty(value = "아이디", dataType = "String", required = true, example = "test")
	private String username;
	
	@NotBlank(message = "비밀번호는 필수 입력 값입니다.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
	@ApiModelProperty(value = "비밀번호", dataType = "String", required = true, example = "1q2w3e!@#")
	private String password;
	
	@NotBlank(message = "성명은 필수 입력 값입니다.")
	@Pattern(regexp = "^[ㄱ-ㅎ가-힣]*$", message = "한글로 입력해주세요.")
	@ApiModelProperty(value = "실명", dataType = "String", required = true, example = "김사원")
	private String name;
	
	@NotBlank(message = "주소는 필수 입력 값입니다.")
	@ApiModelProperty(value = "우편번호", dataType = "String", required = true, example = "다음 open api를 통해 입력")
	private String postCode;
	
	@NotBlank(message = "주소는 필수 입력 값입니다.")
	@ApiModelProperty(value = "주소", dataType = "String", required = true, example = "다음 open api를 통해 입력")
	private String address;
	
	@Nullable
	@ApiModelProperty(value = "상세주소", dataType = "String", required = true, example = "다음 open api를 통해 입력(101동 101호)")
	private String detailAddress;
	
	@Nullable
	@ApiModelProperty(value = "참고항목", dataType = "String", required = true, example = "다음 open api를 통해 입력(여의도동)")
	private String extraAddress;
	
	//에러
	@NotBlank(message = "휴대번호는 필수 입력 값입니다.")
	@Pattern(regexp = "^01(?:0|1[6-9])(\\d{3,4})(\\d{4})", message = "한글로 입력해주세요.")
	@ApiModelProperty(value = "휴대번호", dataType = "String", required = true, example = "01012341234")
	private String mobile;
	
	@NotBlank(message = "이메일은 필수 입력 값입니다.")
	@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "이메일 형식이 올바르지 않습니다.")
	@ApiModelProperty(value = "이메일", dataType = "String", required = true, example = "tset@naver.com")
	private String email;
	
	private EmployeePublic employeePublic;

}