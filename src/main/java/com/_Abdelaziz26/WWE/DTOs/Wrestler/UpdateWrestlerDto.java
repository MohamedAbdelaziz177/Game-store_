package com._Abdelaziz26.WWE.DTOs.Wrestler;

import com._Abdelaziz26.WWE.Enums.PlayerStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Size;

import java.util.Date;

public class UpdateWrestlerDto {

    @Size(max = 50, message = "Ring name must be less than 50 characters")
    private String ringName;


    @Size(max = 20, message = "Height must be less than 20 characters")
    private String height;

    @Size(max = 20, message = "Weight must be less than 20 characters")
    private String weight;

    @Size(max = 50, message = "Hometown must be less than 50 characters")
    private String hometown;

    private Date birthDate;

    @Enumerated(EnumType.STRING)
    private PlayerStatus status;

    private String imageUrl;
}
