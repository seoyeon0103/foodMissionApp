package umc.study.repository.ReviewRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.QReview;
import umc.study.domain.Review;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom{
    private final JPAQueryFactory jpaQueryFactory;
    private final QReview review = QReview.review;

    @Override
    public List<Review> dynamicQueryWithReview(Long storeId){
        BooleanBuilder predicate = new BooleanBuilder();

        if(storeId != null){
            predicate.and(review.store.id.eq(storeId));
        }

        return jpaQueryFactory
                .selectFrom(review)
                .where(predicate)
                .fetch();
    }
}
