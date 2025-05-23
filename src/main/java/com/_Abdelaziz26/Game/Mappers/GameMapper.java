package com._Abdelaziz26.Game.Mappers;

import com._Abdelaziz26.Game.DTOs.Game.CreateGameDto;
import com._Abdelaziz26.Game.DTOs.Game.GameCardDto;
import com._Abdelaziz26.Game.DTOs.Game.ReadGameDto;
import com._Abdelaziz26.Game.DTOs.Game.UpdateGameDto;
import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Model.Platform;
import com._Abdelaziz26.Game.Repositories.GenreRepository;
import com._Abdelaziz26.Game.Repositories.PlatformRepository;
import com._Abdelaziz26.Game.Utility.FileStorageService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GameMapper {

    private final FileStorageService fileStorageService;
    private final GenreRepository genreRepository;
    private final PlatformRepository platformRepository;

    public Game fromDto(CreateGameDto createGameDto) {

        Game game = new Game();

        game.setName(createGameDto.getName());
        game.setDescription(createGameDto.getDescription());
        game.setPrice(createGameDto.getPrice());
        game.setDescription(createGameDto.getDescription());
        game.setGenre(genreRepository.findById(createGameDto.getGenreId()).get());

        createGameDto.getPlatformIds().forEach(platformId -> {
            Platform platform = platformRepository.findById(platformId).get();
            game.getPlatforms().add(platform);
            platform.getGames().add(game);
        });

        try {
            game.setImageUrl(fileStorageService.SaveImgAndGetUrl(createGameDto.getImage()));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return game;
    }

    public static ReadGameDto toDto(Game game) {

        ReadGameDto readGameDto = new ReadGameDto();

        readGameDto.setName(game.getName());

        readGameDto.setDescription(game.getDescription());

        readGameDto.setPrice(game.getPrice());

        readGameDto.setDescription(game.getDescription());

        readGameDto.setGenreName(game.getGenre().getName());

        readGameDto.setImageUrl(game.getImageUrl());

        // ---------------
        readGameDto.setPlatformNames(game.getPlatforms().stream().map(Platform::getName).toList());

        readGameDto.setImageUrl(game.getImageUrl());


        return  readGameDto;
    }

    public  Game fromDto(UpdateGameDto updateGameDto) {

        Game game = new Game();

        game.setName(updateGameDto.getName());
        game.setDescription(updateGameDto.getDescription());
        game.setPrice(updateGameDto.getPrice());
        game.setDescription(updateGameDto.getDescription());
        game.setGenre(genreRepository.findById(updateGameDto.getGenreId()).get());

        updateGameDto.getPlatformIds().forEach(platformId -> {
            Platform platform = platformRepository.findById(platformId).get();
            game.getPlatforms().add(platform);
            platform.getGames().add(game);
        });

        try {
            game.setImageUrl(fileStorageService.SaveImgAndGetUrl(updateGameDto.getImage()));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return game;

    }

    public GameCardDto toDto(Game game, boolean card){
        return new GameCardDto(game.getId(), game.getName(), game.getImageUrl());
    }
}
