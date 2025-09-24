package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.DTOs.Review.CreateReviewDto;
import com._Abdelaziz26.Game.DTOs.Review.ReadReviewDto;
import com._Abdelaziz26.Game.DTOs.Review.UpdateReviewDto;
import com._Abdelaziz26.Game.Mappers.ReviewMapper;
import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Model.Review;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import com._Abdelaziz26.Game.Repositories.ReviewRepository;
import com._Abdelaziz26.Game.Repositories.UserRepository;
import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Errors;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public Result<ReadReviewDto, Error> addReview(@AuthenticationPrincipal User user, CreateReviewDto createReviewDto, Long gameId) {

        Review review = reviewMapper.fromDto(createReviewDto, user, gameId);
        return Result.CreateSuccessResult(reviewMapper.toDto(reviewRepository.save(review)));
    }

    public Result<List<ReadReviewDto>, Error> getAllGameReviews(Long gameId) {

        Optional<Game> game = gameRepository.findById(gameId);

        if(game.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Game not found"));

        List<Review> reviews = reviewRepository.findByGame_Id(game.get().getId()).orElse(new ArrayList<>());

        return Result.CreateSuccessResult(reviews.stream().map(reviewMapper::toDto).toList());

    }

    public Result<List<ReadReviewDto>, Error> getAllUserReviews(Long userId) {

        Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("user not found"));

        List<Review> reviews = reviewRepository.findByUser_Id(user.get().getId()).orElse(new ArrayList<>());

        return Result.CreateSuccessResult(reviews.stream().map(reviewMapper::toDto).toList());

        // try -  return user.getReviews().stream().map(reviewMapper::toDto).toList();
    }

    public Result<ReadReviewDto, Error> updateReview(@AuthenticationPrincipal User user,
                                      UpdateReviewDto updateReviewDto,
                                      Long reviewId) {

        Optional<Review> review = reviewRepository.findById(reviewId);

        if(review.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Review not found"));

        if (!Objects.equals(review.get().getUser().getId(), user.getId())) {
            return Result.CreateErrorResult(Errors.UnauthorizedErr("You are not authorized to update this review"));
        }

        return Result
                .CreateSuccessResult(reviewMapper
                        .toDto(reviewRepository
                                .save(reviewMapper.fromDto(updateReviewDto, reviewId)
                                )
                        )
                );
    }

    public Result<String, Error> deleteReview(Long reviewId, @AuthenticationPrincipal User user) {

        Optional<Review> review = reviewRepository.findById(reviewId);

        if(review.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Review not found"));

        if (!Objects.equals(review.get().getUser().getId(), user.getId())) {
            return Result.CreateErrorResult(Errors.UnauthorizedErr("You are not authorized to delete this review"));
        }

        reviewRepository.deleteById(reviewId);

        return Result.CreateSuccessResult("Review deleted successfully");
    }

}
