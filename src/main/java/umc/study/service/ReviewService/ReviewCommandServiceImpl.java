package umc.study.service.ReviewService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.domain.Store;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.repository.ReviewRepository.ReviewImageRepository;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.repository.StoreRepository.StoreRepository;
import umc.study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.study.web.dto.ReviewDTO.ReviewResponseDTO;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final ReviewImageRepository reviewImageRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public ReviewResponseDTO.completereviewInfo createReview(ReviewRequestDTO.registerreviewInfo request, Long storeId){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> new IllegalArgumentException("Invalid storeId: " + storeId));

        Review newReview = ReviewConverter.toReview(request,store);
        //newReview.setMember(member);

        Review savedReview = reviewRepository.save(newReview);

        return ReviewConverter.toCompleReviewInfo(savedReview);
    }

    @Override
    @Transactional
    public Page<Review> getMyReviewList(Long memberId, Integer page){
        Page<Review> myPage = reviewRepository.findAllBymemberId(memberId, PageRequest.of(page,10));

        return myPage;
    }
}
