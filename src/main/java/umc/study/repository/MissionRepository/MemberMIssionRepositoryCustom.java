package umc.study.repository.MissionRepository;

import org.springframework.data.domain.Page;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;

public interface MemberMIssionRepositoryCustom {
    boolean existsByMemberIdAndMissionIdAndMissionStatus(Long missionId, MissionStatus status);
}
