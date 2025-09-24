package com._Abdelaziz26.Game.Mappers;

import com._Abdelaziz26.Game.DTOs.Game.CreateGameDto;
import com._Abdelaziz26.Game.DTOs.Game.GameCardDto;
import com._Abdelaziz26.Game.DTOs.Game.ReadGameDto;
import com._Abdelaziz26.Game.DTOs.Game.UpdateGameDto;
import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Model.Platform;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import com._Abdelaziz26.Game.Repositories.GenreRepository;
import com._Abdelaziz26.Game.Repositories.PlatformRepository;
import com._Abdelaziz26.Game.Utility.CloudinaryService;
import com._Abdelaziz26.Game.Utility.FileStorageService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GameMapper {

    private final CloudinaryService cloudinaryService;
    private final GenreRepository genreRepository;
    private final PlatformRepository platformRepository;
    private final GameRepository gameRepository;

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
            game.setImageUrl(cloudinaryService.upload(createGameDto.getImage()));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return game;
    }

    public  ReadGameDto toDto(Game game) {

        ReadGameDto readGameDto = new ReadGameDto();

        readGameDto.setId(game.getId());

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

    public  Game fromDto(Long id, UpdateGameDto updateGameDto) {

        Game game = gameRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Game not found"));

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
            game.setImageUrl(cloudinaryService.upload(updateGameDto.getImage()));
        }catch (Exception e){
            throw new RuntimeException(e);
        }

        return game;

    }

    public GameCardDto toDto(Game game, boolean card){
        return new GameCardDto(game.getId(), game.getName(), game.getImageUrl(), game.getPrice());
    }
}
