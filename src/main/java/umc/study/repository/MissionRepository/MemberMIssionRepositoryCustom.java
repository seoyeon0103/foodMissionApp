package umc.study.repository.MissionRepository;

import umc.study.domain.enums.MissionStatus;

public interface MemberMIssionRepositoryCustom {
    boolean existsByMemberIdAndMissionIdAndMissionStatus(Long missionId, MissionStatus status);
}
