package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class User {

    @PositiveOrZero
    private int id;

    @NotBlank(message = "Не введен email")
    @Email(message = "Введен некорректный email")
    private final String email;

    @NotNull(message = "Не введен логин")
    @Pattern(regexp = "\\S+", message = "Введен логин с пробелами")
    private final String login;

    private String name;

    @NotNull(message = "Не введена дата рождения")
    @PastOrPresent
    private final LocalDate birthday;

}