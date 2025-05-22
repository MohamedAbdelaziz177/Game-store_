package com._Abdelaziz26.Game.Repositories;

import com._Abdelaziz26.Game.Model.Platform;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PlatformRepository extends JpaRepository<Platform, Long> {

    Optional<Platform> findByName(String name);
}
