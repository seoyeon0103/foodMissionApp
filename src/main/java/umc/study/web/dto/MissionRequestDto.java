package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDto {
    @Getter
    public static class MissionRequest {
        @NotNull
        private Long storeId;

        @NotNull
        private Integer reward;

        @NotNull
        private String missionSpec;

        @NotNull
        private LocalDate deadline;
    }
}
