package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

public class ReviewResponseDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class completereviewInfo{
        @NotNull
        private Long reviewId;

        @NotNull
        private float rating;

        @NotNull
        private String content;

        private List<ReviewRequestDTO.reviewImageInfo> images;
    }
}
