package com._Abdelaziz26.Game.Repositories;

import com._Abdelaziz26.Game.Model.Review;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Optional<Review> findByGame_Id(Long gameId);

    Optional<Review> findByGame_IdAndUser_Id(Long gameId, Long userId);

    Optional<Review> findByUser_Id(Long userId);
}
