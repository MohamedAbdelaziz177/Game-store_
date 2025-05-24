package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Model.Platform;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import com._Abdelaziz26.Game.Repositories.PlatformRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlatformService {

    private final PlatformRepository platformRepository;
    private final GameRepository gameRepository;

    public boolean isPlatformExists(Long platformId) {
        return platformRepository.existsById(platformId);
    }

    public void AddPlatform(String platform) {

        Platform platform1 = new Platform();

        platform1.setName(platform);

        platformRepository.save(platform1);
    }

    public void DeletePlatform(Long platformId) {

        Platform platform = platformRepository.findById(platformId).orElseThrow(() ->
                new EntityNotFoundException("Platform with id " + platformId + " not found"));

        platformRepository.delete(platform);
    }

    public void AddGameToPlatform(Long gameId, Long platformId) {

        Game game = gameRepository.findById(gameId).orElseThrow(() ->
                new EntityNotFoundException("Game with id " + gameId + " not found"));

        Platform platform = platformRepository.findById(platformId).orElseThrow(() ->
                new EntityNotFoundException("Platform with id " + platformId + " not found"));

        game.getPlatforms().add(platform);
        platform.getGames().add(game);

    }

    public void DeleteGameFromPlatform(Long gameId, Long platformId) {

        Game game = gameRepository.findById(gameId).orElseThrow(() ->
                new EntityNotFoundException("Game with id " + gameId + " not found"));

        Platform platform = platformRepository.findById(platformId).orElseThrow(() ->
                new EntityNotFoundException("Platform with id " + platformId + " not found"));

        game.getPlatforms().remove(platform);
        platform.getGames().remove(game);
    }
}
