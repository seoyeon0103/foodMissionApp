package umc.study.service.ReviewService;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.study.web.dto.ReviewDTO.ReviewResponseDTO;

public interface ReviewCommandService {
    public ReviewResponseDTO.completereviewInfo createReview(ReviewRequestDTO.registerreviewInfo request, Long storeId);
    public Page<Review> getMyReviewList(Long memberId, Integer page);
}
