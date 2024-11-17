package umc.study.service.MissionService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.convert.CustomConversions;
import org.springframework.stereotype.Service;
import umc.study.converter.MissionConverter;
import umc.study.domain.Mission;
import umc.study.domain.Store;
import umc.study.repository.FoodCategoryRepository.FoodCategoryRepository;
import umc.study.repository.MissionRepository.MemberMissionRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.MissionRequestDto;
import umc.study.web.dto.MissionResponseDto;

@Service
@RequiredArgsConstructor
public class MissionCommandServiceImpl implements MissionCommandService {
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    @Transactional
    public MissionResponseDto.MissionResponse create(Long storeId, MissionRequestDto.MissionRequest request){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid storeId" + storeId));

        Mission newMission = MissionConverter.toMission(request);
        newMission.setStore(store);

        Mission saveMission = memberMissionRepository.save(newMission);

        String storename = storeRepository.getName(storeId);

        String foodcategory = foodCategoryRepository.getFoodCategory(storeId);

        return MissionConverter.tomissionResponse(saveMission,storename,foodcategory);
    }
}
