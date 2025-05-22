package com._Abdelaziz26.Game.Repositories;
import com._Abdelaziz26.Game.Model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WishlistRepository extends JpaRepository<Wishlist, Long> {

    Optional<Wishlist> findByUser_Id(Long userId);

    Optional<Wishlist> findByGame_id(Long gameId);


}
