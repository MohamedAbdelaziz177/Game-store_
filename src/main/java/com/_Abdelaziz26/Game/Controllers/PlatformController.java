package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import com._Abdelaziz26.Game.Services.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/platform")
@RequiredArgsConstructor
public class PlatformController extends _AbdelazizController {

    private final PlatformService platformService;

    @GetMapping("/exists/{platformId}")
    public ResponseEntity<Boolean> isPlatformExists(@PathVariable Long platformId) {

        boolean exists = platformService.isPlatformExists(platformId);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/")
    public ResponseEntity<Result<String, Error>> addPlatform(@RequestBody Map<String, String> map) {
        return ResponseEntity.ok(Result.CreateSuccessResult("Platform added successfully"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Result<Void, Error>> deletePlatform(@PathVariable Long id) {

        Result<Void, Error> res = platformService.deletePlatform(id);
        return ResponseEntity.status(resolveStatus(res)).body(res);
    }

    @PostMapping("/addGame")
    public ResponseEntity<Result<Void, Error>> addGameToPlatform(@RequestParam Long gameId, @RequestParam Long platformId) {

        Result<Void, Error> res = platformService.assignGameToPlatform(gameId, platformId);
        return ResponseEntity.status(resolveStatus(res)).body(res);
    }

    @DeleteMapping("/deleteGame")
    public ResponseEntity<Result<Void, Error>> deleteGameFromPlatform(@RequestParam Long gameId,
                                                                      @RequestParam Long platformId) {

        Result<Void, Error> res = platformService.deleteGameFromPlatform(gameId, platformId);
        return ResponseEntity.status(resolveStatus(res)).body(res);
    }
}
