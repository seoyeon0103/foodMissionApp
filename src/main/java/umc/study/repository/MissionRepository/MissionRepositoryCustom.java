package umc.study.repository.MissionRepository;

import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;

import java.util.List;

public interface MissionRepositoryCustom {
    List<MemberMission> dynamicQueryWithMemberIdAndStatus(Long memberId, MissionStatus status);

    List<MemberMission> dynamicQueryWithMemberIdAndRegion(Long memberId, String region);
    Long dynamicQueryWithtotalCounting(Long memberId, String region);
    Long dynamicQueryWithCompleteCounting(Long memberId, String region);
}
