package umc.study.converter;

import org.springframework.data.domain.Page;
import umc.study.domain.Review;
import umc.study.domain.ReviewImage;
import umc.study.domain.Store;
import umc.study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.study.web.dto.ReviewDTO.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {
    public static ReviewRequestDTO.reviewImageInfo toReviewImageInfo(ReviewImage reviewImage){
        return ReviewRequestDTO.reviewImageInfo.builder()
                .imageUrl(reviewImage.getImageUrl())
                .build();

    }
    public static ReviewResponseDTO.completereviewInfo toCompleReviewInfo(Review review) {
        return ReviewResponseDTO.completereviewInfo.builder()
                .reviewId(review.getId())
                .rating(review.getScore())
                .images(review.getReviews().stream()
                        .map(ReviewConverter::toReviewImageInfo)
                        .collect(Collectors.toList())
                )
                .build();
    }

    public static Review toReview(ReviewRequestDTO.registerreviewInfo request, Store store) {
        Review review = Review.builder()
                .score(request.getRating())
                .body(request.getContent())
                .store(store)
                .build();

        if(request.getImages() != null){
            List<ReviewImage> reviewImages = request.getImages().stream()
                    .map(imageInfo -> ReviewImage.builder()
                            .imageUrl(imageInfo.getImageUrl())//url
                            .review(review)//review 연관관계 설정
                            .build())
                    .collect(Collectors.toList());

            review.setReviews(reviewImages);
        }

        return review;
    }

    public static ReviewResponseDTO.reviewDetailResponse toReviewDetail(Review review){
        return ReviewResponseDTO.reviewDetailResponse.builder()
                .nickName(review.getMember().getName())
                .score(review.getScore())
                .createAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponseDTO.reviewListViewResponse toReviewList(Page<Review> reviewList){
        List<ReviewResponseDTO.reviewDetailResponse> reviewDetailList =
                reviewList.stream()
                        .map(ReviewConverter::toReviewDetail)
                        .collect(Collectors.toList());

        return ReviewResponseDTO.reviewListViewResponse.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewDetailList.size())
                .reviewList(reviewDetailList)
                .build();
    }

    public static ReviewResponseDTO.myReviewDetailResponse toMyReviewDetail(Review review){
        return ReviewResponseDTO.myReviewDetailResponse.builder()
                .nickName(review.getMember().getName())
                .score(review.getScore())
                .storeName(review.getStore().getName())
                .createAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponseDTO.myReviewListViewResponse toMyReviewList(Page<Review> myReviewList){
        List<ReviewResponseDTO.myReviewDetailResponse> reviewDetailList =
                myReviewList.stream()
                        .map(ReviewConverter::toMyReviewDetail)
                        .collect(Collectors.toList());

        return ReviewResponseDTO.myReviewListViewResponse.builder()
                .isLast(myReviewList.isLast())
                .isFirst(myReviewList.isFirst())
                .totalPage(myReviewList.getTotalPages())
                .totalElements(myReviewList.getTotalElements())
                .listSize(reviewDetailList.size())
                .myReviewList(reviewDetailList)
                .build();
    }
}
