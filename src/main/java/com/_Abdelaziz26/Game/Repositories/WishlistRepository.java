package com._Abdelaziz26.Game.Repositories;
import com._Abdelaziz26.Game.Model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    Optional<List<Wishlist>> findByUser_Id(Long userId);

    Optional<List<Wishlist>> findByGame_id(Long gameId);

    Optional<Wishlist> findByUser_IdAndGame_id(Long userId, Long gameId);

    Boolean existsByUser_IdAndGame_id(Long userId, Long gameId);


}
