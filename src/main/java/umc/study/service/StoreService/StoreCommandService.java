package umc.study.service.StoreService;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.web.dto.ReviewDTO.ReviewResponseDTO;

public interface StoreCommandService {
    public boolean ExistingId(Long id);
    public Page<Review> getReviewList(Long storeId,Integer page);
}
