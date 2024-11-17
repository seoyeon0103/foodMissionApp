package umc.study.service.ReviewService;

import umc.study.domain.Review;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

public interface ReviewCommandService {
    ReviewResponseDTO.completereviewInfo createReview(ReviewRequestDTO.registerreviewInfo request, Long storeId);
}
