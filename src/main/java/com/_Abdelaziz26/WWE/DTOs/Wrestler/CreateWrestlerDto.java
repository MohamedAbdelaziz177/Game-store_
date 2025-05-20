package com._Abdelaziz26.WWE.DTOs.Wrestler;

import com._Abdelaziz26.WWE.Enums.PlayerStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CreateWrestlerDto {

    @NotBlank(message = "Ring name is required")
    @Size(min = 5, max = 50, message = "Ring name must be less than 50 characters")
    private String ringName;

    @Size(min = 5, max = 50, message = "Real name must be less than 50 characters")
    private String realName;

    @Size(min = 5, max = 20, message = "Height must be less than 20 characters")
    private String height;

    @Size(min = 5, max = 20, message = "Weight must be less than 20 characters")
    private String weight;

    @Size(min = 5, max = 50, message = "Hometown must be less than 50 characters")
    private String hometown;

    private Date birthDate;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private PlayerStatus status = PlayerStatus.ACTIVE;


    private String imageUrl;
}
