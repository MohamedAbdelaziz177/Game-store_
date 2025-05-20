package com._Abdelaziz26.WWE.DTOs.Venue;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateVenueDto {

    @NotBlank(message = "Venue name is required")
    @Size(max = 30, message = "Venue name must be less than 100 characters")
    private String name;

    @NotBlank(message = "City is required")
    @Size(max = 30, message = "City must be less than 50 characters")
    private String city;

    @Size(max = 30, message = "State must be less than 50 characters")
    private String street;

    @NotBlank(message = "Country is required")
    @Size(max = 30, message = "Country must be less than 50 characters")
    private String country;

    @NotNull(message = "Capacity is required")
    @PositiveOrZero(message = "Capacity must be positive")
    private Integer capacity;
}
