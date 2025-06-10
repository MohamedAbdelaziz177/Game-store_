package com._Abdelaziz26.Game.Repositories;

import com._Abdelaziz26.Game.Model.Review;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    @Query("select rev from Review rev where rev.game.id = :gameId")
    Optional<List<Review>> findByGame_Id(@Param("gameId") Long gameId);

    Optional<Review> findByGame_IdAndUser_Id(Long gameId, Long userId);

    Optional<List<Review>> findByUser_Id(Long userId);
}
