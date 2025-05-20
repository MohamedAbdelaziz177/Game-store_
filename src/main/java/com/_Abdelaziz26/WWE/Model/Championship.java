package com._Abdelaziz26.WWE.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "championships")
public class Championship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "wrestler_id", nullable = false)
    private Wrestler wrestler;

    @ManyToOne
    @JoinColumn(name = "title_id", nullable = false)
    private Title title;

    @NotNull
    private Date wonDate;

    @NotNull
    private Date lostDate;

    private Integer daysHeld;

    @OneToOne
    @JoinColumn(name = "winning_match_id")
    private Match wonMatch;

    @OneToOne
    @JoinColumn(name = "loss_match_id")
    private Match lostMatch;

}
