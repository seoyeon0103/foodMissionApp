package umc.study.service.MissionService;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import umc.study.domain.Mission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberMissionDTO.MemberMissionResponseDTO;
import umc.study.web.dto.MissionDTO.MissionRequestDto;
import umc.study.web.dto.MissionDTO.MissionResponseDto;

public interface MissionCommandService {
    public MissionResponseDto.MissionResponse create(Long storeId, MissionRequestDto.MissionRequest request);

    public MemberMissionResponseDTO register(Long memberId, Long storeId);

    boolean existsByMemberIdAndMissionIdAndMissionStatus(Long missionId, MissionStatus status);

    public Page<MemberMission> getMyChallengingMissions(Long memberId, Integer page);
    public Page<Mission> getStoreMissions(Long storeId, Integer page);
}