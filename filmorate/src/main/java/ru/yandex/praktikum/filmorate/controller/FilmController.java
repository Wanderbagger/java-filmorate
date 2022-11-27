package ru.yandex.praktikum.filmorate.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.yandex.praktikum.filmorate.model.Film;
import ru.yandex.praktikum.filmorate.validation.ValidationException;
import ru.yandex.praktikum.filmorate.validation.Validator;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(value = "/films")
@Slf4j
public class FilmController {
    private Map<Long, Film> films = new HashMap<>();

    private final Validator validator;

    private long currentId = 1;

    public FilmController(Validator validator) {
        this.validator = validator;
    }

    @GetMapping
    public Collection<Film> getFilms() {
        return films.values();
    }

    @PostMapping
    public Film addFilm(@Valid @RequestBody Film film) {
        try {
            validator.validateRequestBody(film);
        } catch (ValidationException e) {
            log.warn(e.getMessage() + "\n" + film);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        film.setId(currentId);
        films.put(currentId, film);
        log.info("Фильм {} был добавлен", film.getName());
        currentId++;

        return film;
    }


    @PutMapping
    public Film updateFilm(@Valid @RequestBody Film film) {
        try {
            validator.validateRequestBody(film);
        } catch (ValidationException exception) {
            log.warn(exception.getMessage() + "\n" + film);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        Optional<Film> filmToBeUpdated = films.values().stream()
                .filter(x -> x.getId() == film.getId())
                .findAny();

        long id = filmToBeUpdated.map(Film::getId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
        film.setId(id);
        films.put(id, film);
        log.info("Фильм {} был обновлен", film.getName());

        return film;
    }
}