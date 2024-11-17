package umc.study.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.Member;
import umc.study.domain.Region;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.MissionRepository.MemberMissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public List<MemberMission> findMissionByMemberIdAndStatus(Long memberId, MissionStatus status) {
        List<MemberMission> filterdMissions = memberMissionRepository.dynamicQueryWithMemberIdAndStatus(memberId, status);

        filterdMissions.forEach(memberMission -> System.out.println("Mission" + memberMission));

        return filterdMissions;
    }

    @Override
    public List<MemberMission> findMyHomeInfo(Long memberId, String region) {
        List<MemberMission> filteredMissions = memberMissionRepository.dynamicQueryWithMemberIdAndRegion(memberId, region);
        Long filterTotalCounting = memberMissionRepository.dynamicQueryWithtotalCounting(memberId,region);
        Long filterCompleteCounting = memberMissionRepository.dynamicQueryWithCompleteCounting(memberId,region);

        System.out.println("total: " + filterTotalCounting);
        System.out.println("complete: " + filterCompleteCounting);

        filteredMissions.forEach(homePageInfo -> System.out.println("Mission" + homePageInfo ));

        return filteredMissions;
    }
}
