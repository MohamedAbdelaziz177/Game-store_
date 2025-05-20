package com._Abdelaziz26.WWE.Repositories;

import com._Abdelaziz26.WWE.Model.MatchParticipant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipantsRepository extends JpaRepository<MatchParticipant, Long> {

    Page<MatchParticipant> findParticipantsByWrestler_Id(Long wrestlerId, Pageable pageable);

    Page<MatchParticipant> findMatchParticipantsByMatch_Id(Long matchId, Pageable pageable);
}
