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
	
	@NotBlank(message = "¾ÆÀÌµğ´Â ÇÊ¼ö ÀÔ·Â °ªÀÔ´Ï´Ù.")
	@ApiModelProperty(value = "¾ÆÀÌµğ", dataType = "String", required = true, example = "test")
	private String username;
	
	@NotBlank(message = "ºñ¹Ğ¹øÈ£´Â ÇÊ¼ö ÀÔ·Â °ªÀÔ´Ï´Ù.")
	@Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,16}$", message = "ºñ¹Ğ¹øÈ£´Â 8~16ÀÚ ¿µ¹® ´ë ¼Ò¹®ÀÚ, ¼ıÀÚ, Æ¯¼ö¹®ÀÚ¸¦ »ç¿ëÇÏ¼¼¿ä.")
	@ApiModelProperty(value = "ºñ¹Ğ¹øÈ£", dataType = "String", required = true, example = "1q2w3e!@#")
	private String password;
	
	@NotBlank(message = "¼º¸íÀº ÇÊ¼ö ÀÔ·Â °ªÀÔ´Ï´Ù.")
	@Pattern(regexp = "^[¤¡-¤¾°¡-ÆR]*$", message = "ÇÑ±Û·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.")
	@ApiModelProperty(value = "½Ç¸í", dataType = "String", required = true, example = "±è»ç¿ø")
	private String name;
	
	@NotBlank(message = "ÁÖ¼Ò´Â ÇÊ¼ö ÀÔ·Â °ªÀÔ´Ï´Ù.")
	@ApiModelProperty(value = "¿ìÆí¹øÈ£", dataType = "String", required = true, example = "´ÙÀ½ open api¸¦ ÅëÇØ ÀÔ·Â")
	private String postCode;
	
	@NotBlank(message = "ÁÖ¼Ò´Â ÇÊ¼ö ÀÔ·Â °ªÀÔ´Ï´Ù.")
	@ApiModelProperty(value = "ÁÖ¼Ò", dataType = "String", required = true, example = "´ÙÀ½ open api¸¦ ÅëÇØ ÀÔ·Â")
	private String address;
	
	@Nullable
	@ApiModelProperty(value = "»ó¼¼ÁÖ¼Ò", dataType = "String", required = true, example = "´ÙÀ½ open api¸¦ ÅëÇØ ÀÔ·Â(101µ¿ 101È£)")
	private String detailAddress;
	
	@Nullable
	@ApiModelProperty(value = "Âü°íÇ×¸ñ", dataType = "String", required = true, example = "´ÙÀ½ open api¸¦ ÅëÇØ ÀÔ·Â(¿©ÀÇµµµ¿)")
	private String extraAddress;
	
	//¿¡·¯
	@NotBlank(message = "ÈŞ´ë¹øÈ£´Â ÇÊ¼ö ÀÔ·Â °ªÀÔ´Ï´Ù.")
	@Pattern(regexp = "^01(?:0|1[6-9])(\\d{3,4})(\\d{4})", message = "ÇÑ±Û·Î ÀÔ·ÂÇØÁÖ¼¼¿ä.")
	@ApiModelProperty(value = "ÈŞ´ë¹øÈ£", dataType = "String", required = true, example = "01012341234")
	private String mobile;
	
	@NotBlank(message = "ÀÌ¸ŞÀÏÀº ÇÊ¼ö ÀÔ·Â °ªÀÔ´Ï´Ù.")
	@Pattern(regexp = "^(?:\\w+\\.?)*\\w+@(?:\\w+\\.)+\\w+$", message = "ÀÌ¸ŞÀÏ Çü½ÄÀÌ ¿Ã¹Ù¸£Áö ¾Ê½À´Ï´Ù.")
	@ApiModelProperty(value = "ÀÌ¸ŞÀÏ", dataType = "String", required = true, example = "tset@naver.com")
	private String email;
	
	private EmployeePublic employeePublic;

}