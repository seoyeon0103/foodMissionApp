package umc.study.repository.ReviewRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import umc.study.domain.Review;
import umc.study.domain.Store;

import java.util.List;

public interface ReviewRepositoryCustom {
    List<Review> dynamicQueryWithReview(Long storeId);
}
