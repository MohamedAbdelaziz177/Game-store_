package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Model.Platform;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import com._Abdelaziz26.Game.Repositories.PlatformRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlatformService {

    private final PlatformRepository platformRepository;
    private final GameRepository gameRepository;

    public boolean isPlatformExists(Long platformId) {
        return platformRepository.existsById(platformId);
    }


    @Cacheable(value = "AllPlatforms")
    public List<String> getAllPlatforms() {
        List<Platform> platforms = platformRepository.findAll();
        return platforms.stream().map(Platform::getName).toList();
    }

    @CacheEvict(value = "AllPlatforms", allEntries = true)
    public void addPlatform(String platform) {

        Platform platform1 = new Platform();

        platform1.setName(platform);

        platformRepository.save(platform1);
    }

    @CacheEvict(value = "AllPlatforms", allEntries = true)
    public void deletePlatform(Long platformId) {

        Platform platform = platformRepository.findById(platformId).orElseThrow(() ->
                new EntityNotFoundException("Platform with id " + platformId + " not found"));

        platformRepository.delete(platform);
    }

    public void assignGameToPlatform(Long gameId, Long platformId) {

        Game game = gameRepository.findById(gameId).orElseThrow(() ->
                new EntityNotFoundException("Game with id " + gameId + " not found"));

        Platform platform = platformRepository.findById(platformId).orElseThrow(() ->
                new EntityNotFoundException("Platform with id " + platformId + " not found"));

        game.getPlatforms().add(platform);
        platform.getGames().add(game);

        gameRepository.save(game);

    }

    public void deleteGameFromPlatform(Long gameId, Long platformId) {

        Game game = gameRepository.findById(gameId).orElseThrow(() ->
                new EntityNotFoundException("Game with id " + gameId + " not found"));

        Platform platform = platformRepository.findById(platformId).orElseThrow(() ->
                new EntityNotFoundException("Platform with id " + platformId + " not found"));

        game.getPlatforms().remove(platform);
        platform.getGames().remove(game);

        gameRepository.save(game);
    }
}
