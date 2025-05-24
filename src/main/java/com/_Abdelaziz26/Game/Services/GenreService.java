package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.Model.Genre;
import com._Abdelaziz26.Game.Repositories.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public void addGenre(String genre) {

        Genre newGenre = new Genre();
        newGenre.setName(genre);
        genreRepository.save(newGenre);
    }

    public void removeGenre(String genre) {

        Genre genreToRemove = genreRepository.findByName(genre).orElseThrow(() ->
                new RuntimeException("Genre not found"));

        genreRepository.delete(genreToRemove);
    }

    public List<String> getAllGenres() {

        List<Genre> genres = genreRepository.findAll();

        return genres.stream().map(Genre::getName).toList();
    }
}
