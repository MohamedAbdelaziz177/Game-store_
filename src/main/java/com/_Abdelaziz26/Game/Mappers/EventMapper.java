package com._Abdelaziz26.Game.Mappers;

import org.mapstruct.Mapper;

@Mapper
public interface EventMapper {



    //@Mapping(target = "venue", expression = "java(mapVenue(dto.getVenueId()))")
    //@Mapping(target = "images", source = "images")
    //Event toEntity(CreateEventDto dto);
//
    //@Mapping(target = "venue", expression = "java(mapVenue(dto.getVenueId()))")
    //@Mapping(target = "images", source = "images")
    //Event toEntity(UpdateEventDto dto);
//
//
    //@Mapping(source = "venue.id", target = "venueId")
    //@Mapping(source = "matches.size", target = "matchCount")
    //ReadEventDto toDTO(Event event);
//
//
    //default Venue mapVenue(Long venueId) {
    //    if (venueId == null) return null;
    //    Venue venue = new Venue();
    //    venue.setId(venueId);
    //    return venue;
    //}



    // Map images from outside in service easier -_-

}
