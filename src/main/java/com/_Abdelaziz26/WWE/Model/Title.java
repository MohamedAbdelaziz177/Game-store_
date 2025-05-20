package com._Abdelaziz26.WWE.Model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "titles")
@Data
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private Date createdAt;

    @OneToMany(mappedBy = "title", cascade = CascadeType.ALL)
    private List<Championship> championships;
}
