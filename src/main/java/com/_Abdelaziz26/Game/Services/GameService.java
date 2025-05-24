package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.DTOs.Game.CreateGameDto;
import com._Abdelaziz26.Game.DTOs.Game.GameCardDto;
import com._Abdelaziz26.Game.DTOs.Game.ReadGameDto;
import com._Abdelaziz26.Game.DTOs.Game.UpdateGameDto;
import com._Abdelaziz26.Game.Mappers.GameMapper;
import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper mapper;

    public ReadGameDto addGame(CreateGameDto dto)
    {
        return mapper.toDto(gameRepository.save(mapper.fromDto(dto)));
    }

    public ReadGameDto updateGame(Long id, UpdateGameDto dto)
    {
        return mapper.toDto(gameRepository.save(mapper.fromDto(id, dto)));
    }

    public void deleteGame(Long id)
    {
        Game game = gameRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Game not found"));

        gameRepository.delete(game);
    }

    public ReadGameDto getGameById(Long id)
    {
        Game game = gameRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Game not found"));

        return mapper.toDto(game);
    }



    public List<GameCardDto> getAllGames(int pageIndex, int pageSize, String sortField, String sortDirection)
    {
        Pageable pageable = getPageAndSorting(pageIndex, pageSize, sortField, sortDirection);

        Page<Game> games = gameRepository.findAll(pageable);

        return games.stream().map(game -> mapper.toDto(game, true)).toList();

    }


    public List<GameCardDto> filterGames( int pageIndex,
                                    int pageSize,
                                    String sortField,
                                    String sortDirection,
                                    Specification<Game> filter
    ){

        Pageable pageable = getPageAndSorting(pageIndex, pageSize, sortField, sortDirection);

        if (filter == null)
            return getAllGames(pageIndex, pageSize, sortField, sortDirection);


        Page<Game> games = gameRepository.findAll(filter, pageable);

        return games.stream().map(game -> mapper.toDto(game, true)).toList();
    }


    private Pageable getPageAndSorting(int pageIndex, int pageSize, String sortField, String sortDirection)
    {
        Sort sort = Sort.by(sortField);

        if (sortDirection.equalsIgnoreCase("ASC")) sort = sort.ascending();
        else sort = sort.descending();

        return PageRequest.of(pageIndex, pageSize, sort);
    }
}
