package umc.study.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.study.apiPayload.exception.PageValidationException;
import umc.study.converter.MissionConverter;
import umc.study.converter.MissionMemberConverter;
import umc.study.domain.Member;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.MissionRepository.MissionRepository;
import umc.study.repository.MissionRepository.MemberMissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.MemberMissionDTO.MemberMissionResponseDTO;
import umc.study.web.dto.MissionDTO.MissionRequestDto;
import umc.study.web.dto.MissionDTO.MissionResponseDto;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public MissionResponseDto.MissionResponse create(Long storeId, MissionRequestDto.MissionRequest request){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid storeId" + storeId));

        Mission newMission = MissionConverter.toMission(request);
        newMission.setStore(store);

        Mission saveMission = missionRepository.save(newMission);

        String storename = storeRepository.getName(storeId);

        String foodcategory = store.getFoodCategory().getName();

        return MissionConverter.tomissionResponse(saveMission,storename,foodcategory);
    }

    @Override
    @Transactional
    public MemberMissionResponseDTO register(Long memberId, Long missionId){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid memberId" + memberId));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid missionId" + missionId));

        MemberMission newMemberMission = MemberMission.builder()
                .member(member)
                .mission(mission)
                .missionStatus(MissionStatus.CHALLENGING)
                .build();

        MemberMission savedMemberMission = memberMissionRepository.save(newMemberMission);

        return MissionMemberConverter.toMemberMission(savedMemberMission);


    }

    @Override
    public boolean existsByMemberIdAndMissionIdAndMissionStatus(Long missionId, MissionStatus status){
        boolean exists =
                memberMissionRepository.existsByMemberIdAndMissionIdAndMissionStatus
                        (missionId, MissionStatus.CHALLENGING);

        return exists;
    }

    @Override
    public Page<MemberMission> getMyChallengingMissions(Long memberId, Integer page){
        Page<MemberMission> myMyMissions =
                memberMissionRepository.findById(memberId, PageRequest.of(page, 10));

        return myMyMissions;
    }

    @Override
    public Page<Mission> getStoreMissions(Long storeId, Integer page){
        Page<Mission> storeMissions =
                missionRepository.getMissionsByStoreId(storeId, PageRequest.of(page, 10));

        return storeMissions;
    }
}
