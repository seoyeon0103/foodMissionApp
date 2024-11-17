package umc.study.web.dto;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MissionResponseDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionResponse {
        @NotNull
        private Long missionId;

        @NotNull
        private Long memberMissionId;
    }
}
