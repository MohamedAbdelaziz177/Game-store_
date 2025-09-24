package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.DTOs.Review.CreateReviewDto;
import com._Abdelaziz26.Game.DTOs.Review.ReadReviewDto;
import com._Abdelaziz26.Game.DTOs.Review.UpdateReviewDto;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import com._Abdelaziz26.Game.Services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/review")
@RequiredArgsConstructor
public class ReviewController extends _AbdelazizController {

    private final ReviewService reviewService;

    @PostMapping("/{gameId}")
    public ResponseEntity<Result<ReadReviewDto, Error>> addReview(
            @RequestBody CreateReviewDto createReviewDto,
            @PathVariable Long gameId,
            @AuthenticationPrincipal User user) {

        Result<ReadReviewDto, Error> result = reviewService.addReview(user, createReviewDto, gameId);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

    @GetMapping("/gameId/{gameId}")
    public ResponseEntity<Result<List<ReadReviewDto>, Error>> getAllGameReviews(@PathVariable Long gameId) {

        Result<List<ReadReviewDto>, Error> result = reviewService.getAllGameReviews(gameId);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<Result<List<ReadReviewDto>, Error>> getAllUserReviews(@PathVariable Long userId) {

        Result<List<ReadReviewDto>, Error> result = reviewService.getAllUserReviews(userId);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Result<ReadReviewDto, Error>> updateReview(
            @RequestBody UpdateReviewDto updateReviewDto,
            @PathVariable Long reviewId,
            @AuthenticationPrincipal User user) {

        Result<ReadReviewDto, Error> result = reviewService.updateReview(user, updateReviewDto, reviewId);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Result<String, Error>> deleteReview(
            @PathVariable Long reviewId,
            @AuthenticationPrincipal User user) {

        Result<String, Error> result = reviewService.deleteReview(reviewId, user);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }


}
