package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.study.domain.Review;
import umc.study.repository.ReviewRepository.ReviewRepository;

import java.util.List;

import static umc.study.domain.QReview.review;

@Service
@RequiredArgsConstructor
public class ReveiwQueryServiceImpl implements ReviewQueryService{
    private final ReviewRepository reviewRepository;

    @Override
    public List<Review> findReviewByStoreId(Long storeId) {
        List<Review> filteredReview = reviewRepository.dynamicQueryWithReview(storeId);

        return filteredReview;
    }
}
