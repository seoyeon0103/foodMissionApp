package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.apiPayload.code.status.ErrorStatus;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.service.MemberService.MemberCommandService;
import umc.study.validation.annotation.ExistCategories;
import umc.study.validation.annotation.ExistMember;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MyMemberExistValidator implements ConstraintValidator<ExistMember, Long> {
    private final MemberCommandService memberCommandService;

    @Override
    public void initialize(ExistMember constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long memberId, ConstraintValidatorContext context) {
        boolean isValid = memberCommandService.findExistingIds(memberId);

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.MEMBER_NOT_FOUND.toString()).addConstraintViolation();
        }

        return isValid;

    }
}
