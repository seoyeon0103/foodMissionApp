package umc.study.service.StoreService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.ReviewDTO.ReviewResponseDTO;

@Service
@RequiredArgsConstructor
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public boolean ExistingId(Long id){
        boolean isValid = storeRepository.existsById(id);
        return isValid;
    }

    @Override
    @Transactional
    public Page<Review> getReviewList(Long storeId,Integer page){
        Store store = storeRepository.findById(storeId).get();

        Page<Review> StorePage = storeRepository.findReviewsWithMemberByStoreId(storeId, PageRequest.of(page,10));
        return StorePage;
    }
}
