package com._Abdelaziz26.Game.Mappers;

import com._Abdelaziz26.Game.DTOs.Review.CreateReviewDto;
import com._Abdelaziz26.Game.DTOs.Review.ReadReviewDto;
import com._Abdelaziz26.Game.DTOs.Review.UpdateReviewDto;
import com._Abdelaziz26.Game.Model.Review;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import com._Abdelaziz26.Game.Repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.Locked;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMapper {

    private final GameRepository gameRepository;
    private final ReviewRepository reviewRepository;

    public Review fromDto(CreateReviewDto createReviewDto, @AuthenticationPrincipal User user, Long gameId) {
        return Review.builder()
                .comment(createReviewDto.getContent())
                .rating(createReviewDto.getRating())
                .user(user)
                .game(gameRepository.findById(gameId).orElseThrow(() -> new EntityNotFoundException("Game not found")))
                .build();
    }

    public ReadReviewDto toDto(Review review) {
        return ReadReviewDto.builder()
                .id(review.getId())
                .content(review.getComment())
                .rating(review.getRating())
                .userName(review.getUser().getEmail())
                .build();
    }

    public Review toDto(UpdateReviewDto updateReviewDto, Long id) {

        Review review = reviewRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Review not found"));

        review.setComment(updateReviewDto.getContent());
        review.setRating(updateReviewDto.getRating());


        return review;
    }
}
