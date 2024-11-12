package umc.study.service.ReviewService;

import umc.study.domain.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewQueryService {
    List<Review> findReviewByStoreId(Long storeId);
}
