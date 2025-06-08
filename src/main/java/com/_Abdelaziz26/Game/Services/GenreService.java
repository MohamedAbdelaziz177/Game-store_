package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.Model.Genre;
import com._Abdelaziz26.Game.Repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    @CacheEvict(value = "AllGenres", allEntries = true)
    public void addGenre(String genre) {

        Genre newGenre = new Genre();
        newGenre.setName(genre);
        genreRepository.save(newGenre);
    }

    @CacheEvict(value = "AllGenres", allEntries = true)
    public void removeGenre(String genre) {

        Genre genreToRemove = genreRepository.findByName(genre).orElseThrow(() ->
                new RuntimeException("Genre not found"));

        genreRepository.delete(genreToRemove);
    }

    @Cacheable(value = "AllGenres")
    public List<String> getAllGenres() {

        List<Genre> genres = genreRepository.findAll();

        return genres.stream().map(Genre::getName).toList();
    }
}
