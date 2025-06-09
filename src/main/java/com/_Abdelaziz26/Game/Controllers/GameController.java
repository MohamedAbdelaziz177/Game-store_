package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.DTOs.Game.CreateGameDto;
import com._Abdelaziz26.Game.DTOs.Game.GameCardDto;
import com._Abdelaziz26.Game.DTOs.Game.ReadGameDto;
import com._Abdelaziz26.Game.DTOs.Game.UpdateGameDto;
import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Responses.ApiResponse;
import com._Abdelaziz26.Game.Services.GameService;
import com._Abdelaziz26.Game.Utility.GameSpecifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ApiResponse<ReadGameDto>> getGameById(@PathVariable Long id) {

        ApiResponse<ReadGameDto> res = new ApiResponse<>();

        res.setData(gameService.getGameById(id));
        res.setSuccess(true);
        res.setMessage("Games retrieved successfully");

        return ResponseEntity.ok(res);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponse<List<GameCardDto>>> getAllGames (
                                         @RequestParam(defaultValue = "0") int pageIdx,
                                         @RequestParam(defaultValue = "8") int pageSize,
                                         @RequestParam(required = false, defaultValue = "price") String sortField,
                                         @RequestParam(required = false, defaultValue = "asc") String sortDirection,
                                         @RequestParam(required = false) String genre,
                                         @RequestParam(required = false) String platform,
                                         @RequestParam(required = false) String search
    ) {

        //Specification<Game> spec = Specification.where(null);
        //
        //if(genre != null)
        //    spec = spec.and(GameSpecifications.hasField(genre, genreValue));
        //
        //
        //if(platform != null)
        //    spec = spec.and(GameSpecifications.hasField(platform, platformValue));
        //
        //if(search != null)
        //    spec = spec.and(GameSpecifications.hasFieldLike(search, search));


        ApiResponse<List<GameCardDto>> res = new ApiResponse<>();

        res.setData(gameService.filterGames(pageIdx, pageSize, sortField, sortDirection, genre, platform, search));

        res.setSuccess(true);
        res.setMessage("Games retrieved successfully");

        return ResponseEntity.ok(res);

    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<ReadGameDto>> addGame(@ModelAttribute CreateGameDto game) {

        ApiResponse<ReadGameDto> res = new ApiResponse<>();

        res.setData(gameService.addGame(game));
        res.setSuccess(true);
        res.setMessage("Game added successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(res);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable Long id) {

        gameService.deleteGame(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ReadGameDto>> updateGame(@PathVariable Long id, UpdateGameDto updateGameDto) {

        ApiResponse<ReadGameDto> res = new ApiResponse<>();

        res.setData(gameService.updateGame(id, updateGameDto));
        res.setSuccess(true);
        res.setMessage("Game updated successfully");

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(res);
    }
}
