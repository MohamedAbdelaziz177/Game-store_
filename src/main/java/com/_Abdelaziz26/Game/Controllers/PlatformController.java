package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.Services.PlatformService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/platform")
@RequiredArgsConstructor
public class PlatformController {

    private final PlatformService platformService;

    @GetMapping("/exists/{platformId}")
    public ResponseEntity<Boolean> isPlatformExists(@PathVariable Long platformId) {

        boolean exists = platformService.isPlatformExists(platformId);
        return ResponseEntity.ok(exists);
    }

    @PostMapping("/")
    public ResponseEntity<String> addPlatform(@RequestBody String platform) {

        platformService.addPlatform(platform);
        return ResponseEntity.ok("Platform added successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePlatform(@PathVariable Long id) {
        platformService.deletePlatform(id);
        return ResponseEntity.ok("Platform deleted successfully");
    }

    @PostMapping("/addGame")
    public ResponseEntity<String> addGameToPlatform(@RequestParam Long gameId, @RequestParam Long platformId) {
        platformService.assignGameToPlatform(gameId, platformId);
        return ResponseEntity.ok("Game added to platform successfully");
    }

    @DeleteMapping("/deleteGame")
    public ResponseEntity<String> deleteGameFromPlatform(@RequestParam Long gameId, @RequestParam Long platformId) {
        platformService.deleteGameFromPlatform(gameId, platformId);
        return ResponseEntity.ok("Game deleted from platform successfully");
    }


}
