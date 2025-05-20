package com._Abdelaziz26.WWE.Model;

import com._Abdelaziz26.WWE.Enums.PlayerStatus;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "wrestlers")
public class Wrestler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String realName;

    private String ringName;

    private Double height;

    private Double weight;

    private Date birthDate;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private PlayerStatus status;

    @OneToMany(orphanRemoval = false, fetch = FetchType.LAZY, mappedBy = "wrestler")
    private List<MatchParticipants> matchParticipants;

    @OneToMany(mappedBy = "wrestler", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Championship> championships;
}
