package com._Abdelaziz26.WWE.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "venues")
public class Venue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true, nullable = false)
    @Size(max = 30)
    private String name;

    @NotBlank
    @Column(unique = true, nullable = false)
    @Size(max = 30)
    private String city;

    @NotBlank
    @Column(unique = true, nullable = false)
    @Size(max = 30)
    private String street;

    @NotBlank
    @Column(unique = true, nullable = false)
    private String country;

    private Integer capacity;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "venue")
    private List<Event> events;
}
