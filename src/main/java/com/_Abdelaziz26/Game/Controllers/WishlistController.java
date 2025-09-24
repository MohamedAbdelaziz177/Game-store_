package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.DTOs.Whishlist.WishListItemDto;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import com._Abdelaziz26.Game.Services.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController extends _AbdelazizController {

    private final WishlistService wishlistService;

    @PostMapping("/wish")
    public ResponseEntity<Result<String, Error>> wishGame(
            @AuthenticationPrincipal User user,
            @RequestParam Long gameId) {

        Result<String, Error> result = wishlistService.wishGame(user, gameId);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

    @DeleteMapping("/un-wish")
    public ResponseEntity<Result<String, Error>> unwishGame(
            @AuthenticationPrincipal User user,
            @RequestParam Long gameId) {

        Result<String, Error> result = wishlistService.unwishGame(user, gameId);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }

    @GetMapping
    public ResponseEntity<Result<List<WishListItemDto>, Error>> getWishlist(
            @AuthenticationPrincipal User user) {

        Result<List<WishListItemDto>, Error> result = wishlistService.getWishlist(user);
        return ResponseEntity.status(resolveStatus(result)).body(result);
    }
}
