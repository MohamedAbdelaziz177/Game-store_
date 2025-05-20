package com._Abdelaziz26.WWE.Repositories;

import com._Abdelaziz26.WWE.Model.MatchParticipants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ParticipantsRepository extends JpaRepository<MatchParticipants, Long> {

    Page<MatchParticipants> findParticipantsByWrestler_Id(Long wrestlerId, Pageable pageable);

    Page<MatchParticipants> findMatchParticipantsByMatch_Id(Long matchId, Pageable pageable);
}
