package com._Abdelaziz26.WWE.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "match_participants")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchParticipants {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "match-id", nullable = false)
    private Match match;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wrestler-id", nullable = false)
    private Wrestler wrestler;

    private boolean isWinner;
}
