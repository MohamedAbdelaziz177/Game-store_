package com._Abdelaziz26.WWE.DTOs.Wrestler;

import com._Abdelaziz26.WWE.Enums.PlayerStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;

import java.util.Date;

@Data
public class ReadWrestlerDto {

    private Long id;

    private String ringName;

    private String realName;

    private Double height;

    private Double weight;

    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private PlayerStatus status;

    private String imageUrl;

    private int championshipCount;

    private int totalMatches;
}
