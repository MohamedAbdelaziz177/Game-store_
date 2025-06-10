package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.DTOs.Review.CreateReviewDto;
import com._Abdelaziz26.Game.DTOs.Review.ReadReviewDto;
import com._Abdelaziz26.Game.DTOs.Review.UpdateReviewDto;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Responses.ApiResponse;
import com._Abdelaziz26.Game.Services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{gameId}")
    public ResponseEntity<ApiResponse<ReadReviewDto>> addReview(@RequestBody CreateReviewDto createReviewDto,
                                                                @PathVariable Long gameId,
                                                                @AuthenticationPrincipal User user) {

        ReadReviewDto reviewDto = reviewService.addReview(user, createReviewDto, gameId);
        return ResponseEntity.ok(new ApiResponse<>(true, reviewDto));
    }

    @GetMapping("/gameId/{gameId}")
    public ResponseEntity<ApiResponse<List<ReadReviewDto>>> getAllGameReviews(@PathVariable Long gameId) {

        List<ReadReviewDto> reviews = reviewService.getAllGameReviews(gameId);
        return ResponseEntity.ok(new ApiResponse<>(true, reviews));
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<ApiResponse<List<ReadReviewDto>>> getAllUserReviews(@PathVariable Long userId) {

        List<ReadReviewDto> reviews = reviewService.getAllUserReviews(userId);
        return ResponseEntity.ok(new ApiResponse<>(true, reviews));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<ReadReviewDto>> updateReview(@RequestBody UpdateReviewDto updateReviewDto,
                                                                   @PathVariable Long reviewId,
                                                                   @AuthenticationPrincipal User user) {

        ReadReviewDto reviewDto = reviewService.updateReview(user, updateReviewDto, reviewId);
        return ResponseEntity.ok(new ApiResponse<>(true, reviewDto));
    }
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<ApiResponse<Void>> deleteReview(@PathVariable Long reviewId, @AuthenticationPrincipal User user) {
        reviewService.deleteReview(reviewId, user);
        return ResponseEntity.ok(new ApiResponse<>(true, null));
    }

}
