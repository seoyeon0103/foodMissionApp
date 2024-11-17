package umc.study.service.MissionService;

import umc.study.web.dto.MissionRequestDto;
import umc.study.web.dto.MissionResponseDto;

public interface MissionCommandService {
    public MissionResponseDto.MissionResponse create(Long storeId, MissionRequestDto.MissionRequest request);
}
