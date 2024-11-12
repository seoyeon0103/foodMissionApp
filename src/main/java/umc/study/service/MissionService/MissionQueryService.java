package umc.study.service.MissionService;

import umc.study.domain.Region;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;

import java.util.List;

public interface MissionQueryService {
    List<MemberMission> findMissionByMemberIdAndStatus(Long memberId, MissionStatus status);
    List<MemberMission> findMyHomeInfo(Long memberId, String region);
}
