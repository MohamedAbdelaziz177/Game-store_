package com._Abdelaziz26.WWE.Repositories;

import com._Abdelaziz26.WWE.Model.Championship;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ChampionshipRepository extends JpaRepository<Championship, Long> {

    Page<Championship> findByWrestlerId(Long wrestlerId, Pageable pageable);

    Page<Championship> findByTitleId(Long titleId, Pageable pageable);

    Page<Championship> findByWonDateBetween(Date start, Date end, Pageable pageable);



    Optional<List<Championship>> findByLostDateBetween(Date startDate, Date endDate);

    Optional<List<Championship>> findByWrestler_IdAndWonDateBetween(Long wrestlerId, Date start, Date end);

    Optional<List<Championship>> findByWrestler_IdAndTitle_Id(Long wrestlerId, Long titleId);



    Page<Championship> findByTitle_IdOrderByDaysHeldDesc(Long titleId, Pageable pageable);

    Optional<List<Championship>> findByTitle_IdOrderByWonDateDesc(Long titleId);

    @Query("SELECT c FROM Championship c WHERE c.title.id = :titleId AND c.lostDate = null")
    Optional<Championship> findCurrentWinnerByTitleId(@Param("titleId") Long titleId);
}
