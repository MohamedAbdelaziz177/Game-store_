package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.Responses.ApiResponse;
import com._Abdelaziz26.Game.Services.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/genre")
@RequiredArgsConstructor
public class GenreController {

    private final GenreService genreService;

    @PostMapping("/")
    public ResponseEntity<ApiResponse<String>> addGenre(@RequestBody String genre) {

        ApiResponse<String> apiResponse = new ApiResponse<>();

        genreService.addGenre(genre);

        apiResponse.setSuccess(Boolean.TRUE);
        apiResponse.setMessage("Successfully added genre");

        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/")
    public ResponseEntity<ApiResponse<String>> removeGenre(@RequestParam String genre) {

        ApiResponse<String> apiResponse = new ApiResponse<>();

        genreService.removeGenre(genre);

        apiResponse.setMessage("Genre removed successfully");
        apiResponse.setSuccess(Boolean.TRUE);

        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<String>>> getAllGenres() {

        ApiResponse<List<String>> apiResponse = new ApiResponse<>();

        List<String> genres = genreService.getAllGenres();

        apiResponse.setSuccess(Boolean.TRUE);
        apiResponse.setMessage("Genres retrieved successfully");
        apiResponse.setData(genres);

        return ResponseEntity.ok(apiResponse);
    }
}
