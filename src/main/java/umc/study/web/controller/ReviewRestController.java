package umc.study.web.controller;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.apiPayload.exception.PageValidationException;
import umc.study.converter.ReviewConverter;
import umc.study.domain.Review;
import umc.study.service.MemberService.MemberCommandService;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.service.StoreService.StoreCommandService;
import umc.study.validation.annotation.ExistMember;
import umc.study.validation.annotation.ExistStore;
import umc.study.web.dto.ReviewDTO.ReviewRequestDTO;
import umc.study.web.dto.ReviewDTO.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;
    private final StoreCommandService storeCommandService;

    @PostMapping("/{storeId}/create")
    public ApiResponse<ReviewResponseDTO.completereviewInfo> create(
            @PathVariable("storeId") Long storeId,
            @RequestBody @Valid ReviewRequestDTO.registerreviewInfo request){

        ReviewResponseDTO.completereviewInfo response = reviewCommandService.createReview(request,storeId);

        return ApiResponse.onSuccess(response);
    }

    @GetMapping("{storeId}/store")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200"
                    , description = "OK, 성공"),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003"
                    , description = "access 토큰을 주세요!"
                    ,content = @Content(schema = @Schema(implementation = ApiResponse.class))),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004"
                    , description = "acess 토큰 만료"
                    ,content = @Content(schema = @Schema(implementation = ApiResponse.class))),

            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006"
                    , description = "acess 토큰 모양이 이상함"
                    ,content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.reviewListViewResponse> getReviewList
            (@ExistStore @PathVariable("storeId") Long storeId,
             @RequestParam(name = "page") Integer page){
        Integer pageCustom;

        if(page > 0 ){
            pageCustom = page -1;
        }else{
            throw new PageValidationException("Page number must be greater than 0");
        }

        Page<Review> reviewList = storeCommandService.getReviewList(storeId, pageCustom);

        return ApiResponse.onSuccess(ReviewConverter.toReviewList(reviewList));
    }

    @GetMapping("{memberId}/my")
    @Parameters({
            @Parameter(name = "memberId", description = "내 아이디, path variable 입니다!")
    })
    public ApiResponse<ReviewResponseDTO.myReviewListViewResponse> getMyReviewList
            (@ExistMember @PathVariable("memberId") Long memberId,
             @RequestParam(name = "page") Integer page){
        Integer pageCustom;

        if(page > 0 ){
            pageCustom = page -1;
        }else{
            throw new PageValidationException("Page number must be greater than 0");
        }

        Page<Review> reviewList = reviewCommandService.getMyReviewList(memberId, pageCustom);

        return ApiResponse.onSuccess(ReviewConverter.toMyReviewList(reviewList));
    }

}
