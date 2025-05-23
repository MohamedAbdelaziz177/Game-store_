package com._Abdelaziz26.Game.Mappers;

import com._Abdelaziz26.Game.DTOs.Review.CreateReviewDto;
import com._Abdelaziz26.Game.DTOs.Review.ReadReviewDto;
import com._Abdelaziz26.Game.DTOs.Review.UpdateReviewDto;
import com._Abdelaziz26.Game.Model.Review;
import com._Abdelaziz26.Game.Model.User;
import lombok.Locked;

public class ReviewMapper {



    public Review fromDto(CreateReviewDto createReviewDto) {
        return Review.builder()
                .comment(createReviewDto.getContent())
                .rating(createReviewDto.getRating())
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

    public Review toDto(UpdateReviewDto updateReviewDto, User user) {
        return Review.builder()
                .comment(updateReviewDto.getContent())
                .rating(updateReviewDto.getRating())
                .user(user)
                .build();
    }
}
