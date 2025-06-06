package com._Abdelaziz26.Game.Repositories;


import com._Abdelaziz26.Game.Model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByName(String name);
}
