package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import spring.RegisterRequest;

public class RegisterRequestValidator implements Validator {
	private static final String emailRegExp = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
		"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
	private Pattern pattern;

	public RegisterRequestValidator() {
		pattern = Pattern.compile(emailRegExp);
	}

	// 이 Validator가 검증할 수 있는, 지원하는 타입인가 확인하는 메소드
	@Override
	public boolean supports(Class<?> aClass) {
		// aClass가 RegisterRequest 타입으로 바뀔 수 있겠느냐~
		return RegisterRequest.class.isAssignableFrom(aClass);
	}

	// 검증 후 오류 결과를 Error에 담아주는 검증 메소드
	@Override
	public void validate(Object target, Errors errors) {
		// 1. 타겟을 실제 타입으로 변환
		RegisterRequest registerRequest = (RegisterRequest)target;

		// 2. 이메일이 존재하지 않으면, "email" 프로퍼티의 에러코드로 "required"를 추가
		if (registerRequest.getEmail() == null || registerRequest.getEmail().trim().isEmpty()) {
			errors.rejectValue("email", "required");
		}

		// 3. 패턴에 맞지 않으면, "email" 프로퍼티의 에러코드로 "bad"을 추가
		else {
			Matcher matcher = pattern.matcher(registerRequest.getEmail());
			if (!matcher.matches()) {
				errors.rejectValue("email", "bad");
			}
		}

		// 4. 객체의 값 검증을 간결하게! 타겟의 "name" 필드가 비었다면, errors의 "name" 프로퍼티로 "required" 에러코드 추가
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required");
		ValidationUtils.rejectIfEmpty(errors, "password", "required");
		ValidationUtils.rejectIfEmpty(errors, "confirmPassword", "required");
		if (!registerRequest.getPassword().isEmpty() && !registerRequest.isPasswordEqualToConfirmPassword()) {
			errors.rejectValue("confirmPassword", "nomatch");
		}
	}
}

