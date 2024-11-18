package umc.study.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.study.apiPayload.ApiResponse;
import umc.study.converter.ReviewConverter;
import umc.study.repository.ReviewRepository.ReviewRepository;
import umc.study.service.ReviewService.ReviewCommandService;
import umc.study.web.dto.ReviewRequestDTO;
import umc.study.web.dto.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping("/{storeId}/create")
    public ApiResponse<ReviewResponseDTO.completereviewInfo> create(
            @PathVariable("storeId") Long storeId,
            @RequestBody @Valid ReviewRequestDTO.registerreviewInfo request){

        ReviewResponseDTO.completereviewInfo response = reviewCommandService.createReview(request,storeId);

        return ApiResponse.onSuccess(response);
    }

}
