package com._Abdelaziz26.Game.Mappers;

import com._Abdelaziz26.Game.DTOs.Review.CreateReviewDto;
import com._Abdelaziz26.Game.DTOs.Review.UpdateReviewDto;
import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Model.Review;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import com._Abdelaziz26.Game.Repositories.ReviewRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ReviewMapperTest {

    @InjectMocks
    private ReviewMapper reviewMapper;

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private GameRepository gameRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFromDto_WhenGameIdAndUserAreValid_ShouldReturnValidEntity() {

        CreateReviewDto dto = new CreateReviewDto();
        dto.setContent("content");
        dto.setRating(5);

        User user = new User();
        user.setId(1L);

        Game game = new Game();
        game.setId(1L);

        when(gameRepository.findById(1L)).thenReturn(Optional.of(game));

        Review review = reviewMapper.fromDto(dto, user, 1L);

        assertNotNull(review);
        assertEquals(dto.getContent(), review.getComment());
        assertEquals(dto.getRating(), review.getRating());
        assertEquals(user.getId(), review.getUser().getId());
        assertEquals(game.getId(), review.getGame().getId());
    }

    @Test
    void testFromCreateDto_WhenGameIdIsInValid_ShouldThrowException() {

        CreateReviewDto dto = new CreateReviewDto();
        dto.setContent("content");
        dto.setRating(5);

        when(gameRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> reviewMapper.fromDto(dto, new User(), 1L));
    }

    @Test
    void testFromUpdateDto_WhenDtoIsValid_ShouldReturnValidEntity() {

        UpdateReviewDto dto = new UpdateReviewDto();
        dto.setContent("content");
        dto.setRating(5);

        when(reviewRepository.findById(1L)).thenReturn(Optional.of(new Review()));

        Review review = reviewMapper.fromDto(dto, 1L);

        assertNotNull(review);
        assertEquals(dto.getContent(), review.getComment());
        assertEquals(dto.getRating(), review.getRating());

    }

    @Test
    void testFromUpdateDto_WhenIdIsInvalid_ShouldThrowException() {

        UpdateReviewDto dto = new UpdateReviewDto();
        dto.setContent("content");
        dto.setRating(5);

        when(reviewRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> reviewMapper.fromDto(dto, 1L));
    }


}