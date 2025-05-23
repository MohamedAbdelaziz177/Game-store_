package com._Abdelaziz26.Game.DTOs.Review;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateReviewDto {

    @Size(max = 300)
    private String content;

    @NotNull
    private int rating;
}
