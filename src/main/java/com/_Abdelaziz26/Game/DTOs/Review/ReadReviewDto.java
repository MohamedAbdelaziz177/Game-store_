package com._Abdelaziz26.Game.DTOs.Review;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReadReviewDto {

    private Long id;

    private String content;

    private int rating;

    private String userName;

}
