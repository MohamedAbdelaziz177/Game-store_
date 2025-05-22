package com._Abdelaziz26.Game.Model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String imageUrl;

    @Column(nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "genre-id")
    private Genre genre;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Wishlist> wishlists;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(

            name = "games-platforms",
            joinColumns = @JoinColumn(name = "game-id", nullable = false),
            inverseJoinColumns = @JoinColumn(name = "platform-id", nullable = false)
    )
    private List<Platform> platforms;


}
