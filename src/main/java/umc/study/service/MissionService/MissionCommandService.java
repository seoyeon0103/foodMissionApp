package umc.study.service.MissionService;

import umc.study.domain.enums.MissionStatus;
import umc.study.web.dto.MemberMissionResponseDTO;
import umc.study.web.dto.MissionRequestDto;
import umc.study.web.dto.MissionResponseDto;

public interface MissionCommandService {
    public MissionResponseDto.MissionResponse create(Long storeId, MissionRequestDto.MissionRequest request);

    public MemberMissionResponseDTO register(Long memberId, Long storeId);
    boolean existsByMemberIdAndMissionIdAndMissionStatus(Long missionId, MissionStatus status);
}
