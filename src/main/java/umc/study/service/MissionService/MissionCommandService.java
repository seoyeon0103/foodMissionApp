package umc.study.service.MissionService;

import umc.study.domain.enums.MissionStatus;
import umc.study.web.dto.MemberMissionDTO.MemberMissionResponseDTO;
import umc.study.web.dto.MissionDTO.MissionRequestDto;
import umc.study.web.dto.MissionDTO.MissionResponseDto;

public interface MissionCommandService {
    public MissionResponseDto.MissionResponse create(Long storeId, MissionRequestDto.MissionRequest request);

    public MemberMissionResponseDTO register(Long memberId, Long storeId);
    boolean existsByMemberIdAndMissionIdAndMissionStatus(Long missionId, MissionStatus status);
}
