package com._Abdelaziz26.WWE.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
@Data
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 2, max = 50)
    private String name;

    private Long attendance;

    private Date date;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<EventImage> images;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "venue_id")
    private Venue venue;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<Match> matches;
}
