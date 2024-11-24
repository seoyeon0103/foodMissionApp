package umc.study.converter;


import org.springframework.data.domain.Page;
import umc.study.domain.mapping.MemberMission;
import umc.study.web.dto.MemberMissionDTO.MemberMissionResponseDTO;
import umc.study.web.dto.MissionDTO.MissionResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class MissionMemberConverter {
    public static MemberMissionResponseDTO toMemberMission(MemberMission memberMission){
        return MemberMissionResponseDTO.builder()
                .memberMissionId(memberMission.getId())
                .storeName(memberMission.getMission().getStore().getName())
                .foodCategory(memberMission.getMission().getStore().getFoodCategory().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .build();
    }

    public static MissionResponseDto.myMissionDetailResponse toMyMemberMission (MemberMission memberMission){
        return MissionResponseDto.myMissionDetailResponse.builder()
                .memberMissionId(memberMission.getId())
                .storeName(memberMission.getMission().getStore().getName())
                .foodCategory(memberMission.getMission().getStore().getFoodCategory().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .build();
    }

    public static MissionResponseDto.myMissionListViewResponse toMyMissionList(Page<MemberMission> myChallengingList){
        List<MissionResponseDto.myMissionDetailResponse> myMissionDetailList =
                myChallengingList.stream()
                        .map(MissionMemberConverter::toMyMemberMission)
                        .collect(Collectors.toList());

        return MissionResponseDto.myMissionListViewResponse.builder()
                .isLast(myChallengingList.isLast())
                .isFirst(myChallengingList.isFirst())
                .totalPage(myChallengingList.getTotalPages())
                .totalElements(myChallengingList.getTotalElements())
                .listSize(myMissionDetailList.size())
                .myMissionList(myMissionDetailList)
                .build();
    }
}
