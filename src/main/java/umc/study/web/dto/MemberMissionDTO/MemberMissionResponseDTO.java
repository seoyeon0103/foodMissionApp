package umc.study.web.dto.MemberMissionDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MemberMissionResponseDTO {

    private Long memberMissionId;
    private String storeName;
    private String foodCategory;
    private String missionSpec;
    private Integer reward;
}