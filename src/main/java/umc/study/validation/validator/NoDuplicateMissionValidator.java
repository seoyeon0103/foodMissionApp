package umc.study.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.study.domain.enums.MissionStatus;
import umc.study.service.MissionService.MissionCommandService;
import umc.study.validation.annotation.NoDuplicationMission;

@Component
@RequiredArgsConstructor
public class NoDuplicateMissionValidator implements ConstraintValidator<NoDuplicationMission, Long> {
    //service 부분으로 바꾸기
    private final MissionCommandService missionCommandService;

    @Override
    public boolean isValid(Long missionId, ConstraintValidatorContext context) {
        boolean exists = missionCommandService.existsByMemberIdAndMissionIdAndMissionStatus(
                missionId, MissionStatus.CHALLENGING);

        if(exists){
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("이미 도전 중")
                    .addConstraintViolation();
        }
        return !exists;
    }
}
