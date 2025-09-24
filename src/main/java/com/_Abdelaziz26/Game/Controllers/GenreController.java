package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import com._Abdelaziz26.Game.Services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/genre")
@RequiredArgsConstructor
public class GenreController extends _AbdelazizController {

    private final GenreService genreService;

    @PostMapping
    public ResponseEntity<Result<String, Error>> addGenre(@RequestBody Map<String, String> map) {
        genreService.addGenre(map.get("genre"));
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Result<String, Error>> removeGenre(@RequestParam Map<String, String> map) {

        Result<String, Error> res = genreService.removeGenre(map.get("genre"));
        return ResponseEntity.status(resolveStatus(res)).body(res);
    }

    @GetMapping("/")
    public ResponseEntity<Result<List<String>, Error>> getAllGenres() {
        return ResponseEntity.ok(genreService.getAllGenres());
    }
}
