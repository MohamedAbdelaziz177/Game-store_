package com._Abdelaziz26.Game.Repositories;
import com._Abdelaziz26.Game.Model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface GameRepository extends JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {

    Page<Game> findByName(String name, Pageable pageable);

    Page<Game> findGamesByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Game> findByGenre_Id(Long genreId, Pageable pageable);


    @Query("SELECT g FROM Game g WHERE g.genre.name = :name ORDER BY g.id DESC")
    Page<Game> findByGenre_Name(@Param("name") String genreName, Pageable pageable);

    // Searching and filtering
    Page<Game> findByGenre_IdAndNameContainingIgnoreCase(Long genreId, String name, Pageable pageable);

    Page<Game> findByNameAndGenre_IdOrderByPriceAsc(String name, Long genreId, Pageable pageable);

    Page<Game> findByNameAndGenre_IdOrderByPriceDesc(String name, Long genreId, Pageable pageable);


}
