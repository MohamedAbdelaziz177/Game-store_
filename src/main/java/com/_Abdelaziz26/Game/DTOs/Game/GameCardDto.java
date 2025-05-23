package com._Abdelaziz26.Game.DTOs.Game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameCardDto {

    private Long id;

    private String name;

    private String imageUrl;
}
