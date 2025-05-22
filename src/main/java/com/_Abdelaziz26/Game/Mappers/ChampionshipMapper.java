package com._Abdelaziz26.Game.Mappers;

import org.mapstruct.Mapper;

@Mapper
public interface ChampionshipMapper {


    //@Mapping(target = "wrestler", expression = "java(mapWrestler(dto.getWrestlerId()))")
    //@Mapping(target = "title", expression = "java(mapTitle(dto.getTitleId()))")
    //@Mapping(target = "wonMatch", expression = "java(mapMatch(dto.getWinningMatchId()))")
    //@Mapping(target = "lostMatch", expression = "java(mapMatch(dto.getLossMatchId()))")
    //Championship toEntity(CreateChampionshipDto dto);
//
//
//
//
    //@Mapping(target = "wrestler", expression = "java(mapWrestler(dto.getWrestlerId()))")
    //@Mapping(target = "title", expression = "java(mapTitle(dto.getTitleId()))")
    //@Mapping(target = "wonMatch", expression = "java(mapMatch(dto.getWinningMatchId()))")
    //@Mapping(target = "lostMatch", expression = "java(mapMatch(dto.getLossMatchId()))")
    //Championship toEntity(UpdateChampionshipDto dto);
//
//
    //@Mapping(source = "wonMatch.id", target = "winningMatchId")
    //@Mapping(source = "lossMatch.id", target = "lossMatchId")
    //@Mapping(source = "title.id", target = "titleId")
    //ReadChampionshipDto toDTO(Championship championship);
//
//
//
    //default Wrestler mapWrestler(Long wrestlerId) {
    //    if (wrestlerId == null) return null;
    //    Wrestler wrestler = new Wrestler();
    //    wrestler.setId(wrestlerId);
    //    return wrestler;
    //}
//
    //default Match mapMatch(Long matchId) {
    //    if (matchId == null) return null;
    //    Match match = new Match();
    //    match.setId(matchId);
//
    //    return match;
    //}
//
    //default Title mapTitle(Long titleId) {
    //    if (titleId == null) return null;
    //    Title title = new Title();
    //    title.setId(titleId);
    //    return title;
    //}


}
