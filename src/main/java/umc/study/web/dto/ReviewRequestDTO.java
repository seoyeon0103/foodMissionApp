package umc.study.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.study.domain.ReviewImage;

import java.util.List;

public class ReviewRequestDTO {
    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class reviewImageInfo{
        @NotNull
        private String imageUrl;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class registerreviewInfo{
        @NotNull
        private float rating;
        @NotNull
        private String content;

        private List<reviewImageInfo> images;
    }
}
