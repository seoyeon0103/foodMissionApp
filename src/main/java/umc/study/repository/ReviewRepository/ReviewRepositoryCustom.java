package umc.study.repository.ReviewRepository;

import umc.study.domain.Review;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> dynamicQueryWithReview(Long storeId);
}
