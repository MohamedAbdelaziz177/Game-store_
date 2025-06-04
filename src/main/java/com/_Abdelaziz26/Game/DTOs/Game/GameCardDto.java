package com._Abdelaziz26.Game.DTOs.Game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameCardDto implements Serializable {

    private Long id;

    private String name;

    private String imageUrl;

    private Double price;
}
