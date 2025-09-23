package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Model.Platform;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import com._Abdelaziz26.Game.Repositories.PlatformRepository;
import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Errors;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public Result<Void, Error> addPlatform(String platform) {

        Platform platform1 = new Platform();
        platform1.setName(platform);
        platformRepository.save(platform1);
        return Result.CreateSuccessResult(null);
    }

    @CacheEvict(value = "AllPlatforms", allEntries = true)
    public Result<Void, Error> deletePlatform(Long platformId) {

        Optional<Platform> platform = platformRepository.findById(platformId);

        if(platform.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Platform with id " + platformId + " not found"));

        platformRepository.delete(platform.get());

        return Result.CreateSuccessResult(null);
    }

    public Result<Void, Error> assignGameToPlatform(Long gameId, Long platformId) {

        Optional<Game> game = gameRepository.findById(gameId);

        if(game.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Game with id " + gameId + " not found"));

        Optional<Platform> platform = platformRepository.findById(platformId);

        if(platform.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Platform with id " + platformId + " not found"));

        game.get().getPlatforms().add(platform.get());
        platform.get().getGames().add(game.get());

        gameRepository.save(game.get());

        return Result.CreateSuccessResult(null);

    }

    public Result<Void, Error> deleteGameFromPlatform(Long gameId, Long platformId) {

        Optional<Game> game = gameRepository.findById(gameId);

        if(game.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Game with id " + gameId + " not found"));

        Optional<Platform> platform = platformRepository.findById(platformId);

        if(platform.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Platform with id " + platformId + " not found"));

        game.get().getPlatforms().remove(platform.get());
        platform.get().getGames().remove(game.get());

        gameRepository.save(game.get());

        return Result.CreateSuccessResult(null);
    }
}
