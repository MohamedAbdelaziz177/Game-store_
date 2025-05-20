package com._Abdelaziz26.WWE.Repositories;

import com._Abdelaziz26.WWE.Model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface EventRepository extends JpaRepository<Event, Long> {

    Optional<Event> findByName(String name);

    Optional<List<Event>> findByVenue_id(Long venueId);

    Page<Event> findByVenue_Id(Long venueId, Pageable pageable);

    Optional<List<Event>> findByDateBetween(Date start, Date end);

}
