package com._Abdelaziz26.Game.Controllers;

import com._Abdelaziz26.Game.DTOs.Whishlist.WishListItemDto;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Model.Wishlist;
import com._Abdelaziz26.Game.Responses.ApiResponse;
import com._Abdelaziz26.Game.Services.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/wish")
    public ResponseEntity<ApiResponse<Void>> wishGame(@AuthenticationPrincipal User user, @RequestParam Long gameId){
        wishlistService.wishGame(user,gameId);
        return ResponseEntity.ok(new ApiResponse<Void>(true, "Game Added to wishlist"));
    }

    @DeleteMapping("/un-wish")
    public ResponseEntity<ApiResponse<Void>> unwishGame(@AuthenticationPrincipal User user, @RequestParam Long gameId){
        wishlistService.unwishGame(user,gameId);
        return ResponseEntity.ok(new ApiResponse<Void>(true, "Game Removed from wishlist"));
    }


    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<WishListItemDto>>> getWishlist(@AuthenticationPrincipal User user){

        return ResponseEntity
                .ok(new ApiResponse<List<WishListItemDto>>(true, wishlistService.getWishlist(user)));
    }

}
