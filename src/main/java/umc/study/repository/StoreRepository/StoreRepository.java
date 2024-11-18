package umc.study.repository.StoreRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.study.domain.Review;
import umc.study.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom{
    @Query("SELECT r FROM Review r JOIN FETCH r.member WHERE r.store.id = :storeId")
    Page<Review> findReviewsWithMemberByStoreId(@Param("storeId") Long storeId, Pageable pageable);
}
