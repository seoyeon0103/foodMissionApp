package umc.study.web.dto.MissionDTO;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

public class MissionResponseDto {
    @Getter
    @Builder
    @AllArgsConstructor
    public static class MissionResponse {
        @NotNull
        private Long missionId;

        @NotNull
        private String storename;

        @NotNull
        private String foodKind;

        @NotNull
        private String content;

        @NotNull
        private Integer reward;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class myMissionDetailResponse{
        private Long memberMissionId;
        private String storeName;
        private String foodCategory;
        private String missionSpec;
        private Integer reward;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class myMissionListViewResponse{
        List<myMissionDetailResponse> myMissionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class storeMissionResponse{
        List<MissionResponse> storeMissionList;
        Integer listSize;
        Integer totalPage;
        Long totalElements;
        Boolean isFirst;
        Boolean isLast;
    }
}
