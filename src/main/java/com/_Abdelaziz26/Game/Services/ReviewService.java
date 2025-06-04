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
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public ReadReviewDto addReview(@AuthenticationPrincipal User user, CreateReviewDto createReviewDto, Long gameId) {

        Review review = reviewMapper.fromDto(createReviewDto, user, gameId);
        return reviewMapper.toDto(reviewRepository.save(review));
    }

    public List<ReadReviewDto> getAllGameReviews(Long gameId) {

        Game game = gameRepository.findById(gameId).orElseThrow(() ->
                new EntityNotFoundException("Game not found"));

        return
                reviewRepository.findByGame_Id(gameId).stream().map(reviewMapper::toDto).toList();
    }

    public List<ReadReviewDto> getAllUserReviews(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("User not found"));

        return reviewRepository.findByUser_Id(user.getId()).stream().map(reviewMapper::toDto).toList();

        // try -  return user.getReviews().stream().map(reviewMapper::toDto).toList();
    }

    public ReadReviewDto updateReview(@AuthenticationPrincipal User user,
                                      UpdateReviewDto updateReviewDto,
                                      Long reviewId) {
        return reviewMapper.toDto(reviewRepository.save(reviewMapper.toDto(updateReviewDto, reviewId)));
    }

    public void deleteReview(Long reviewId, @AuthenticationPrincipal User user) {

        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->
                new EntityNotFoundException("Review not found"));

        if (!Objects.equals(review.getUser().getId(), user.getId())) {
            throw new AccessDeniedException("You are not authorized to delete this review");
        }

        reviewRepository.deleteById(reviewId);
    }

}
