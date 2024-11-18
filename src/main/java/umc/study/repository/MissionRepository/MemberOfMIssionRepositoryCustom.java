package umc.study.repository.MissionRepository;

import umc.study.domain.enums.MissionStatus;

public interface MemberOfMIssionRepositoryCustom {
    boolean existsByMemberIdAndMissionIdAndMissionStatus(Long missionId, MissionStatus status);
}
