package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.DTOs.Whishlist.WishListItemDto;
import com._Abdelaziz26.Game.Mappers.WishlistMapper;
import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Model.Wishlist;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import com._Abdelaziz26.Game.Repositories.WishlistRepository;
import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Errors;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final GameRepository gameRepository;
    private final WishlistMapper wishlistMapper;

    public Result<String, Error> wishGame(@AuthenticationPrincipal User user, Long gameId) {

        Optional<Game> game = gameRepository.findById(gameId);

        if(game.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Game not found"));

        Wishlist wish = new Wishlist();

        wish.setUser(user);
        wish.setGame(game.get());

        wishlistRepository.save(wish);

        return Result.CreateSuccessResult("Game wished");
    }

    public Result<String, Error> unwishGame(@AuthenticationPrincipal User user, Long gameId) {

        Optional<Wishlist> wish = wishlistRepository.findByUser_IdAndGame_id(user.getId(), gameId);

        if(wish.isEmpty())
            return Result.CreateErrorResult(Errors.BadRequestErr("Game not found"));

        user.getWishlists().remove(wish.get());
        wishlistRepository.delete(wish.get());

        return Result.CreateSuccessResult("Game un-wished");
    }

    @Cacheable(value = "WishedGames")
    public Result<List<WishListItemDto>, Error> getWishlist(@AuthenticationPrincipal User user) {

        List<Wishlist> wishlists = wishlistRepository.findByUser_Id(user.getId()).orElse(new ArrayList<>());

        return Result.CreateSuccessResult(wishlists.stream().map(wishlistMapper::toDto).toList());
    }

    public boolean isWished(User user, Long gameId) {
        return wishlistRepository.existsByUser_IdAndGame_id(user.getId(), gameId);
    }
}
