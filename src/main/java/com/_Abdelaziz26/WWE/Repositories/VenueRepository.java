package com._Abdelaziz26.WWE.Repositories;

import com._Abdelaziz26.WWE.Model.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenueRepository extends JpaRepository<Venue, Long> {

    Optional<Venue> findByName(String name);
    Optional<Venue> findByCity(String city);
    Optional<Venue> findByStreet(String street);
}
