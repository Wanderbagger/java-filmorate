package ru.yandex.praktikum.filmorate.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import ru.yandex.praktikum.filmorate.validate.BeginOfCinemaEra;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Film {

    @PositiveOrZero
    private int id;

    @NotBlank(message = "Введено пустое название")
    private final String name;

    @Length(min = 1, max = 200, message = "Значение должно быть между 1 и 200")
    private String description;

    @CorrectReleaseDay(message = "Фильм должен быть выпущен после 28-DEC-1895")
    private LocalDate releaseDate;

    @PositiveOrZero(message = "Продолжительность не может быть отрицательной")
    private Integer duration;

}