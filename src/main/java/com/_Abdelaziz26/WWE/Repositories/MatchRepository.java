package com._Abdelaziz26.WWE.Repositories;

import com._Abdelaziz26.WWE.Model.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MatchRepository extends JpaRepository<Match, Long> {

    Optional<List<Match>> findByDurationInMinutesBetween(int minMinutes, int maxMinutes);

    Page<Match> findByEvent_DateAfter(Date date, Pageable pageable);

    Page<Match> findByEvent_DateBefore(Date date, Pageable pageable);

    Page<Match> findByEvent_DateBetween(Date startDate, Date endDate, Pageable pageable);


    Optional<List<Match>> findByEvent_Id(long eventId);

    @Query("SELECT m FROM Match m WHERE m.event.venue.city = :city")
    Page<Match> findByCity(@Param("city") String city, Pageable pageable);


}
