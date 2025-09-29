package com._Abdelaziz26.Game.Model;

import com._Abdelaziz26.Game.Enums.LicenceStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity @Table(name = "licences") @NoArgsConstructor @AllArgsConstructor @Getter @Setter @Builder
public class Licence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, name = "licence-key")
    private String key;

    @Enumerated(EnumType.STRING)
    private LicenceStatus status = LicenceStatus.ACTIVE;

    @Column(nullable = false, insertable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Future
    private LocalDate expiryDate;

    @OneToOne
    @JoinColumn(name = "purchase-id")
    private Purchase purchase;

    @ManyToOne
    @JoinColumn(name = "user-id")
    private User user;

}
