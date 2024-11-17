package umc.study.converter;

import umc.study.domain.Review;
import umc.study.domain.ReviewImage;
import umc.study.domain.Store;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {
    public static ReviewRequestDTO.reviewImageInfo toReviewImageInfo(ReviewImage reviewImage){
        return ReviewRequestDTO.reviewImageInfo.builder()
                .imageId(reviewImage.getId())
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

            review.getReviews().addAll(reviewImages);
        }

        return review;
    }
}
