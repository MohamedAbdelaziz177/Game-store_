package com._Abdelaziz26.WWE.Repositories;

import com._Abdelaziz26.WWE.Enums.PlayerStatus;
import com._Abdelaziz26.WWE.Model.Wrestler;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WrestlerRepository extends JpaRepository<Wrestler, Long> {

    Optional<Wrestler> findByRealName(String realName);
    Optional<Wrestler> findByRingName(String ringName);

    Optional<Wrestler> findByRingNameStartingWith(String prefix);

    Optional<Wrestler> findByStatusIs(PlayerStatus status);

    Optional<Wrestler> findByStatusNotIn(List<PlayerStatus> statuses);

}
