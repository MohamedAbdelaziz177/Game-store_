package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.DTOs.Game.CreateGameDto;
import com._Abdelaziz26.Game.DTOs.Game.GameCardDto;
import com._Abdelaziz26.Game.DTOs.Game.ReadGameDto;
import com._Abdelaziz26.Game.DTOs.Game.UpdateGameDto;
import com._Abdelaziz26.Game.Mappers.GameMapper;
import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Errors;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import com._Abdelaziz26.Game.Utility.GameSpecifications;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper mapper;

    @Caching(
            evict = @CacheEvict(value = "ALL_GAMES_CACHE", allEntries = true),
            put = @CachePut(value = "GAME_CACHE", key = "#result.id")
    )
    public Result<ReadGameDto, Error> addGame(CreateGameDto dto)
    {
        return Result.CreateSuccessResult(mapper.toDto(gameRepository.save(mapper.fromDto(dto))));
    }



    @Caching(
            evict = @CacheEvict(value = "ALL_GAMES_CACHE", allEntries = true),
            put = @CachePut(value = "GAME_CACHE", key = "#id")
    )
    public Result<ReadGameDto, Error> updateGame(Long id, UpdateGameDto dto)
    {
        return Result.CreateSuccessResult(mapper.toDto(gameRepository.save(mapper.fromDto(id, dto))));
    }

    @Caching(
            evict = {
                    @CacheEvict(value = "GAME_CACHE", key = "#id"),
                    @CacheEvict(value = "ALL_GAMES_CACHE", allEntries = true)
            }
    )
    public Result<String, Error> deleteGame(Long id)
    {
        Optional<Game> game = gameRepository.findById(id);

        if(game.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Game not found"));

        gameRepository.delete(game.get());

        return Result.CreateSuccessResult("Game deleted successfully");
    }


    @Cacheable(value = "GAME_CACHE", key = "#id")
    public Result<ReadGameDto, Error> getGameById(Long id)
    {
        Optional<Game> game = gameRepository.findById(id);

        if(game.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Game not found"));

        return Result.CreateSuccessResult(mapper.toDto(game.get()));
    }


    @Cacheable(value = "ALL_GAMES_CACHE", key = "{#pageIndex, #pageSize, #sortField, #sortDirection}")
    public Result<List<GameCardDto>, Error> getAllGames(int pageIndex, int pageSize, String sortField, String sortDirection)
    {
        Pageable pageable = getPageAndSorting(pageIndex, pageSize, sortField, sortDirection);

        Page<Game> games = gameRepository.findAll(pageable);

        return Result
                .CreateSuccessResult(games
                .stream()
                .map(game -> mapper.toDto(game, true))
                .toList());

    }


    @Cacheable(value = "ALL_GAMES_CACHE", key = "{#pageIndex, #pageSize, #sortField, #sortDirection, #genre, #platform, #search}")
    public Result<List<GameCardDto>, Error> filterGames( int pageIndex,
                                    int pageSize,
                                    String sortField,
                                    String sortDirection,
                                    Optional<String> genre,
                                    Optional<String> platform,
                                    Optional<String> search,
                                    Optional<Double> minPrice,
                                    Optional<Double> maxPrice
    ){

        Map<String, Object> filters = new HashMap<>();

        if (search.isPresent()) filters.put("search", search);
        if (genre.isPresent())   filters.put("genre", genre);
        if (platform.isPresent()) filters.put("platform", platform);
        if (minPrice.isPresent()) filters.put("minPrice", minPrice);
        if (maxPrice.isPresent()) filters.put("maxPrice", maxPrice);

        Pageable pageable = getPageAndSorting(pageIndex, pageSize, sortField, sortDirection);
        Specification<Game> specs = GameSpecifications.getSpecifications(filters);

        Page<Game> games = gameRepository.findAll(specs, pageable);

        return Result.CreateSuccessResult(games
                .stream()
                .map(game -> mapper.toDto(game, true))
                .toList());
    }


    private Pageable getPageAndSorting(int pageIndex, int pageSize, String sortField, String sortDirection)
    {
        Sort sort = Sort.by(sortField);

        if (sortDirection.equalsIgnoreCase("ASC")) sort = sort.ascending();
        else sort = sort.descending();

        return PageRequest.of(pageIndex, pageSize, sort);
    }
}
