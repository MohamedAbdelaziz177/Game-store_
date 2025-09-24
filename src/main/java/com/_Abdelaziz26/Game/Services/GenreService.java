package com._Abdelaziz26.Game.Services;

import com._Abdelaziz26.Game.Model.Genre;
import com._Abdelaziz26.Game.Repositories.GenreRepository;
import com._Abdelaziz26.Game.Responses.Result_.Error;
import com._Abdelaziz26.Game.Responses.Result_.Errors;
import com._Abdelaziz26.Game.Responses.Result_.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    @CacheEvict(value = "AllGenres", allEntries = true)
    public Result<Void, Error> addGenre(String genre) {

        Genre newGenre = new Genre();
        newGenre.setName(genre);
        genreRepository.save(newGenre);

        return Result.CreateSuccessResult(null);
    }

    @CacheEvict(value = "AllGenres", allEntries = true)
    public Result<String, Error> removeGenre(String genre) {

        Optional<Genre> genreToRemove = genreRepository.findByName(genre);

        if(genreToRemove.isEmpty())
            return Result.CreateErrorResult(Errors.NotFoundErr("Genre not found"));

        genreRepository.delete(genreToRemove.get());

        return Result.CreateSuccessResult("Genre removed successfully");
    }

    @Cacheable(value = "AllGenres")
    public Result<List<String>, Error> getAllGenres() {

        List<Genre> genres = genreRepository.findAll();

        return Result.CreateSuccessResult(genres.stream().map(Genre::getName).toList());
    }
}
