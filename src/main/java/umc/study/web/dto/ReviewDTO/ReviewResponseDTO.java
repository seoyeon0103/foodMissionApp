package umc.study.web.dto.ReviewDTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class reviewDetailResponse{
        String nickName;
        Float score;
        String body;
        LocalDate createAt;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class reviewListViewResponse{
        List<reviewDetailResponse> reviewList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
}
