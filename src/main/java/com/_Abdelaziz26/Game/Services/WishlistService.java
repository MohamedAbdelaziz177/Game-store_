package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.DTOs.Whishlist.WishListItemDto;
import com._Abdelaziz26.Game.Mappers.WishlistMapper;
import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Model.User;
import com._Abdelaziz26.Game.Model.Wishlist;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import com._Abdelaziz26.Game.Repositories.WishlistRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {

    private final WishlistRepository wishlistRepository;
    private final GameRepository gameRepository;
    private final WishlistMapper wishlistMapper;

    public void wishGame(User user, Long gameId) {

        Game game = gameRepository.findById(gameId).orElseThrow(EntityNotFoundException::new);
        Wishlist wish = new Wishlist();

        wish.setUser(user);
        wish.setGame(game);

        wishlistRepository.save(wish);
    }

    public void unwishGame(User user, Long gameId) {

        Wishlist wish = wishlistRepository.findByUser_IdAndGame_id(user.getId(), gameId).orElseThrow(() ->
                new EntityNotFoundException("Wishlist not found"));

        user.getWishlists().remove(wish);
        wishlistRepository.delete(wish);

    }

    public List<WishListItemDto> getWishlist(User user) {

        List<Wishlist> wishlists = wishlistRepository.findByUser_Id(user.getId()).orElse(new ArrayList<>());

        return wishlists.stream().map(wishlistMapper::toDto).toList();
    }

    public boolean isWished(User user, Long gameId) {
        return wishlistRepository.existsByUser_IdAndGame_id(user.getId(), gameId);
    }
}
