package com._Abdelaziz26.WWE.Repositories;

import com._Abdelaziz26.WWE.Model.Title;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TitleRepository extends JpaRepository<Title, Integer> {

    Optional<Title> findByName(String name);
}
