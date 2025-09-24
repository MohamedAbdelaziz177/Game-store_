package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.DTOs.Game.CreateGameDto;
import com._Abdelaziz26.Game.DTOs.Game.GameCardDto;
import com._Abdelaziz26.Game.DTOs.Game.ReadGameDto;
import com._Abdelaziz26.Game.DTOs.Game.UpdateGameDto;
import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import com._Abdelaziz26.Game.Services.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController extends _AbdelazizController {

    private final GameService gameService;

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<Result<ReadGameDto, Error>> getGameById(@PathVariable Long id) {

        Result<ReadGameDto, Error> res = gameService.getGameById(id);
        return ResponseEntity.status(resolveStatus(res)).body(res);
    }

    @GetMapping("/get-all")
    public ResponseEntity<Result<List<GameCardDto>, Error>> getAllGames (
                                         @RequestParam(defaultValue = "0") int pageIdx,
                                         @RequestParam(defaultValue = "3") int pageSize,
                                         @RequestParam(required = false, defaultValue = "price") String sortField,
                                         @RequestParam(required = false, defaultValue = "asc") String sortDirection,
                                         @RequestParam Optional<String> genre,
                                         @RequestParam Optional<String> platform,
                                         @RequestParam Optional<String> search,
                                         @RequestParam Optional<Double> minPrice,
                                         @RequestParam Optional<Double> maxPrice
    ) {

        Result<List<GameCardDto>, Error> res = gameService.filterGames(
                pageIdx,
                pageSize,
                sortField,
                sortDirection,
                genre,
                platform,
                search,
                minPrice,
                maxPrice
        );

        return ResponseEntity.status(resolveStatus(res)).body(res);
    }

    @PostMapping("/add")
    public ResponseEntity<Result<ReadGameDto, Error>> addGame(@ModelAttribute CreateGameDto game) {
        return ResponseEntity.status(HttpStatus.CREATED).body(gameService.addGame(game));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Result<String, Error>> deleteGame(@PathVariable Long id) {

        Result<String, Error> res = gameService.deleteGame(id);
        return ResponseEntity.status(resolveStatus(res)).body(res);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Result<ReadGameDto, Error>> updateGame(@PathVariable Long id, UpdateGameDto updateGameDto) {

        Result<ReadGameDto, Error> res = gameService.updateGame(id, updateGameDto);
        return ResponseEntity.status(resolveStatus(res)).body(res);
    }
}
