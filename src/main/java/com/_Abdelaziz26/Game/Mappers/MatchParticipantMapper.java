package com._Abdelaziz26.Game.Mappers;

import org.mapstruct.Mapper;

@Mapper
public interface MatchParticipantMapper {

    //@Mapping(target = "wrestler", expression = "java(mapMatch(dto.getWrestlerId()))")
    //MatchParticipant toEntity(CreateParticipantDto dto);
//
    //@Mapping(target = "wrestler", expression = "java(mapMatch(dto.getWrestlerId()))")
    //MatchParticipant toEntity(UpdateParticipantDto dto);
//
    //@Mapping(source = "wrestler", target = "wrestler")
    //ReadParticipantDto toDTO(MatchParticipant matchParticipant);
//
    //default Wrestler mapWrestler(Long wrestlerId) {
    //    if (wrestlerId == null) return null;
    //    Wrestler wrestler = new Wrestler();
    //    wrestler.setId(wrestlerId);
    //    return wrestler;
    //}
}
