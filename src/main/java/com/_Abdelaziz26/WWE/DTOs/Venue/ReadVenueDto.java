package com._Abdelaziz26.WWE.DTOs.Venue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadVenueDto {

    private Long id;

    private String name;

    private String city;

    private String street;

    private String country;

    private Integer capacity;

    private Integer eventsCount;
}
