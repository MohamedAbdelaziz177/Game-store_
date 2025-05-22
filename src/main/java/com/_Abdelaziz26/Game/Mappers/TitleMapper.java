package com._Abdelaziz26.Game.Mappers;

import lombok.RequiredArgsConstructor;
import org.mapstruct.Mapper;

@Mapper
@RequiredArgsConstructor
public abstract class TitleMapper {

    //private final FileStorageService fileStorageService;

    //public abstract SimpleTitleDto toDto(Title title);

    //@Mapping(target = "imageUrl",
    //        expression = "java(fileStorageService.SaveImgAndGetUrl(dto.getImage(), \"titles\"))"
    //)
    //public abstract Title toTitle(CreateTitleDto dto);
}
