package com._Abdelaziz26.Game.DTOs.Game;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadGameDto implements Serializable {

    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    private double price;

    private String genreName;

    private List<String> platformNames;

}
