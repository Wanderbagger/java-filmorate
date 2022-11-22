package ru.yandex.praktikum.filmorate.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import lombok.Data;

@Data
public class User {
    @PositiveOrZero(message = "id должен быть неотрицательным")
    private int id;

    @NotBlank(message = "email не должен быть пустым")
    @Email(message = "некорректный email")
    private String email;

    @NotBlank(message = "login не должен быть пустым")
    private String login;

    private String name;

    @PastOrPresent
    private LocalDate birthday;
}
