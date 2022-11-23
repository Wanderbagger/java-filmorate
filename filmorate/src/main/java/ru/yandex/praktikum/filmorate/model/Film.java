package ru.yandex.praktikum.filmorate.model;

import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class Film {

    @PositiveOrZero
    private int id;

    @NotBlank(message = "Введено пустое название")
    private final String name;
    @Getter
    @Length(min = 1, max = 200, message = "Значение должно быть между 1 и 200")
    private String description;

    @NotBlank(message = "Дата не введена")
    private LocalDate releaseDate;

    @PositiveOrZero(message = "Продолжительность не может быть отрицательной")
    private Integer duration;

}