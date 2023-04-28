package com.project.domain.sign;

import com.project.domain.user.SexType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpForm {
    @NotBlank(message = "아이디는 필수입력 입니다.")
    private String loginId;

    @NotBlank(message = "사용자 이름을 입력해주세요.")
    private String userName;

    @NotBlank
    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$", message = "비밀번호는 영어와 숫자로 포함해서 8~20자리 이내로 입력해주세요.")
    private String password;

    @NotBlank
    @Pattern(regexp="^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$", message = "비밀번호는 영어와 숫자로 포함해서 8~20자리 이내로 입력해주세요.")
    private String passwordCheck;

    @Email(message = "유효하지 않은 이메일입니다.")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;

    @NotBlank(message = "전화번호를 입력해 주세요.")
    private String phone;

    @NotBlank(message = "주소를 입력해 주세요.")
    private String address;

    @NotNull(message = "성별을 선택해주세요.")
    @Enumerated(value = EnumType.STRING)
    private SexType sex;
}
