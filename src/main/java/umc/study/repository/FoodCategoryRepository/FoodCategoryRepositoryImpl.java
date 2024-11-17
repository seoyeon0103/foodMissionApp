package umc.study.repository.FoodCategoryRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.QFoodCategory;

@Repository
@RequiredArgsConstructor
@Transactional
public class FoodCategoryRepositoryImpl implements FoodCategoryRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private QFoodCategory foodCategory;

    @Override
    public String getFoodCategory(Long storeId){
        BooleanBuilder predicate = new BooleanBuilder();

        if(storeId != null){
            predicate.and(foodCategory.id.eq(storeId));
        }

        return queryFactory
                .select(foodCategory.name)
                .from(foodCategory)
                .where(predicate)
                .fetchOne();
    }
}
