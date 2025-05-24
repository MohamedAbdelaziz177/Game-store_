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
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;
    private final GameRepository gameRepository;
    private final UserRepository userRepository;

    public ReadReviewDto addReview(User user , CreateReviewDto createReviewDto, Long gameId) {

        Review review = reviewMapper.fromDto(createReviewDto, user, gameId);
        return reviewMapper.toDto(reviewRepository.save(review)) ;
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

    public ReadReviewDto updateReview(User user, UpdateReviewDto updateReviewDto, Long reviewId) {
        return reviewMapper.toDto(reviewRepository.save(reviewMapper.toDto(updateReviewDto, reviewId)));
    }

}
