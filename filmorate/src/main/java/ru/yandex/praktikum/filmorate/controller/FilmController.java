package ru.yandex.praktikum.filmorate.controller;

import org.springframework.web.bind.annotation.*;
import ru.yandex.praktikum.filmorate.exception.ValidationException;
import ru.yandex.praktikum.filmorate.model.Film;
import ru.yandex.praktikum.filmorate.model.User;

import javax.swing.*;
import javax.validation.Valid;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/films")
public class FilmController {

    private final static LocalDate FIRST_FILM_DATE = LocalDate.of(1895, 12, 28);
    private final static int MAX_SIZE_DESCRIPTION = 200;
    int id = 1;

    Map<Integer, Film> films = new HashMap<>();


    @GetMapping
    public Map<Integer, Film> getUsers() {
        return films;
    }

    @PostMapping
    public Film postFilm(@Valid @RequestBody Film film) {
        checkReleaseDate(film);
        film.setId(id++);
        films.put(film.getId(), film);
        return film;
    }

    @PutMapping
    public Film putFilm(@Valid @RequestBody Film film) {
        checkReleaseDate(film);
        films.put(film.getId(), film);
        return film;
    }

    private void checkReleaseDate(Film film) {
        if (!film.getReleaseDate().isBefore(FIRST_FILM_DATE)) {
            throw new ValidationException("Кино не существовало до " + FIRST_FILM_DATE.toString());
        }
    }
}