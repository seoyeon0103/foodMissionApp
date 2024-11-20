package umc.study.service.ReviewService;

import umc.study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.study.web.dto.ReviewDTO.ReviewResponseDTO;

public interface ReviewCommandService {
    public ReviewResponseDTO.completereviewInfo createReview(ReviewRequestDTO.registerreviewInfo request, Long storeId);
}
