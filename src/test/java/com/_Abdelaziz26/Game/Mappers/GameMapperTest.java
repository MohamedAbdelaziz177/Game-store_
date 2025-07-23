package com._Abdelaziz26.Game.Mappers;

import com._Abdelaziz26.Game.DTOs.Game.CreateGameDto;
import com._Abdelaziz26.Game.DTOs.Game.UpdateGameDto;
import com._Abdelaziz26.Game.Model.Game;
import com._Abdelaziz26.Game.Model.Genre;
import com._Abdelaziz26.Game.Model.Platform;
import com._Abdelaziz26.Game.Repositories.GameRepository;
import com._Abdelaziz26.Game.Repositories.GenreRepository;
import com._Abdelaziz26.Game.Repositories.PlatformRepository;
import com._Abdelaziz26.Game.Utility.FileStorageService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class GameMapperTest {

    @InjectMocks
    private  GameMapper gameMapper;

    @Mock
    private GameRepository gameRepository;

    @Mock
    private FileStorageService fileStorageService;

    @Mock
    private GenreRepository genreRepository;

    @Mock
    private PlatformRepository platformRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFromCrateDto_ShouldReturnValidEntity() throws IOException {
        CreateGameDto dto = new CreateGameDto();
        dto.setName("FIFA");
        dto.setDescription("Football game");
        dto.setPrice(15.5);
        dto.setGenreId(1L);
        dto.setPlatformIds(List.of(1L, 2L));

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Sports");

        Platform platform1 = new Platform();
        platform1.setId(1L);
        platform1.setName("PC");

        Platform platform2 = new Platform();
        platform2.setId(2L);
        platform2.setName("PS");

        when(genreRepository.findById(1L)).thenReturn(java.util.Optional.of(genre));
        when(platformRepository.findById(1L)).thenReturn(Optional.of(platform1));
        when(platformRepository.findById(2L)).thenReturn(Optional.of(platform2));
        when(fileStorageService.SaveImgAndGetUrl(any())).thenReturn("images/uuid");

        Game game = gameMapper.fromDto(dto);

        assertEquals(dto.getName(), game.getName());
        assertEquals(dto.getDescription(), game.getDescription());
        assertEquals(dto.getPrice(), game.getPrice());
        assertEquals(dto.getGenreId(), game.getGenre().getId());
        assertEquals(dto.getPlatformIds().size(), game.getPlatforms().size());
        assertEquals("images/uuid", game.getImageUrl());

    }

    @Test
    public void ShouldThrowExceptionWhenCreateDtoIsNull()
    {
        assertThrows(NullPointerException.class, () -> gameMapper.fromDto(null));
    }

    @Test
    public void testFromUpdateDto_ShouldReturnValidEntity()
    {
        UpdateGameDto dto = new UpdateGameDto();

        dto.setName("FIFA");
        dto.setDescription("Football game");
        dto.setPrice(15.5);
        dto.setGenreId(1L);
        dto.setPlatformIds(List.of(1L, 2L));

        Genre genre = new Genre();
        genre.setId(1L);
        genre.setName("Sports");

        Platform platform1 = new Platform();
        platform1.setId(1L);
        platform1.setName("PC");

        Platform platform2 = new Platform();
        platform2.setId(2L);
        platform2.setName("PS");

        when(gameRepository.findById(any())).thenReturn(Optional.of(new Game()));
        when(platformRepository.findById(any())).thenReturn(Optional.of(platform1));
        when(platformRepository.findById(any())).thenReturn(Optional.of(platform2));
        when(genreRepository.findById(any())).thenReturn(Optional.of(genre));

        Game game = gameMapper.fromDto(1L, dto);

        assertEquals(dto.getName(), game.getName());
        assertEquals(dto.getDescription(), game.getDescription());
        assertEquals(dto.getPrice(), game.getPrice());
        assertEquals(dto.getGenreId(), game.getGenre().getId());
        assertEquals(dto.getPlatformIds().size(), game.getPlatforms().size());

    }

    @Test
    public void testFromUpdateDtoShouldThrowExceptionWhenInvalidId()
    {
        UpdateGameDto dto = new UpdateGameDto();
        when(gameRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> gameMapper.fromDto(1L, dto));
    }

}