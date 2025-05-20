package com._Abdelaziz26.WWE.DTOs.Venue;

import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

public class UpdateVenueDto {

    @Size(max = 30, message = "Venue name must be less than 100 characters")
    private String name;

    @PositiveOrZero(message = "Capacity must be positive")
    private Integer capacity;

}
