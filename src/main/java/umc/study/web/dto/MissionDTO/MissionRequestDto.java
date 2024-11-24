package umc.study.web.dto.MissionDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.web.dto.ReviewDTO.ReviewResponseDTO;

import java.time.LocalDate;
import java.util.List;

public class MissionRequestDto {
    @Getter
    public static class MissionRequest {
        @NotNull
        private Integer reward;

        @NotNull
        private String missionSpec;

        @NotNull
        private LocalDate deadline;
    }

}
