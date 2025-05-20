package com._Abdelaziz26.WWE.DTOs.Event;

import com._Abdelaziz26.WWE.DTOs.EventImage.ReadEventImageDto;
import com._Abdelaziz26.WWE.DTOs.Venue.SimpleVenueDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReadEventDto {

    private Long id;

    private String name;

    private Date date;

    private Integer matchCount;

    private Integer attendance = 10000;

    private SimpleVenueDto venue;

    private List<ReadEventImageDto> images;

}
