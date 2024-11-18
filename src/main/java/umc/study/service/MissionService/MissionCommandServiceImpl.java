package umc.study.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.converter.MissionConverter;
import umc.study.converter.MissionMemberConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.MissionRepository.MemberMissionRepository;
import umc.study.repository.MissionRepository.MemberOfMissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.MemberMissionResponseDTO;
import umc.study.web.dto.MissionRequestDto;
import umc.study.web.dto.MissionResponseDto;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final MemberRepository memberRepository;
    private final MemberOfMissionRepository memberOfMissionRepository;

    @Override
    @Transactional
    public MissionResponseDto.MissionResponse create(Long storeId, MissionRequestDto.MissionRequest request){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid storeId" + storeId));

        Mission newMission = MissionConverter.toMission(request);
        newMission.setStore(store);

        Mission saveMission = memberMissionRepository.save(newMission);

        String storename = storeRepository.getName(storeId);

        String foodcategory = store.getFoodCategory().getName();

        return MissionConverter.tomissionResponse(saveMission,storename,foodcategory);
    }

    @Override
    @Transactional
    public MemberMissionResponseDTO register(Long memberId, Long missionId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid memberId" + memberId));
        Mission mission = memberMissionRepository.findById(missionId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid missionId" + missionId));

        MemberMission newMemberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .missionStatus(MissionStatus.CHALLENGING)
                .build();

        MemberMission savedMemberMission = memberOfMissionRepository.save(newMemberMission);

        return MissionMemberConverter.toMemberMission(savedMemberMission);


    }

    @Override
    public boolean existsByMemberIdAndMissionIdAndMissionStatus(Long missionId, MissionStatus status){
        boolean exists =
                memberOfMissionRepository.existsByMemberIdAndMissionIdAndMissionStatus
                        (missionId, MissionStatus.CHALLENGING);

        return exists;
    }
}
