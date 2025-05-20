package com._Abdelaziz26.WWE.Model;

import com._Abdelaziz26.WWE.Enums.MatchType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "matches")
@NoArgsConstructor
@AllArgsConstructor
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private MatchType matchType;

    @NotNull
    private Date appointment;

    private Integer durationInMinutes;

    @OneToMany(fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "match")
    private List<MatchParticipants> participants;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event-id", nullable = false)
    private Event event;
}
