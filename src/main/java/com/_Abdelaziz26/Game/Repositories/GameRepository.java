package com._Abdelaziz26.Game.Repositories;
import com._Abdelaziz26.Game.Model.Game;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface GameRepository extends JpaRepository<Game, Long>, JpaSpecificationExecutor<Game> {

    Page<Game> findByName(String name, Pageable pageable);

    Page<Game> findGamesByNameContainingIgnoreCase(String name, Pageable pageable);

    Page<Game> findByGenre_Id(Long genreId, Pageable pageable);


    Page<Game> filterByGenreAndPlatforms(Specification<Game> specification, Pageable pageable);


}
