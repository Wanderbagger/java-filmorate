package ru.yandex.praktikum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.yandex.praktikum.filmorate.exception.ValidationException;
import ru.yandex.praktikum.filmorate.model.User;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;


//@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);


    Map<Integer, User> users = new HashMap<>();
    int id = 1;

    @GetMapping
    public Collection<User> getUsers() {
        log.info("GET /users. Количество пользователей: {}", users.size());
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return users.values();
    }

    @PostMapping
    public User postUser(@Valid @RequestBody User user) {
        user.setId(id++);
        ageCheck(user);
        loginCheck(user);
        users.put(user.getId(), user);
        log.info("POST /users. Количество пользователей: {}", users.size());
        return user;
    }

    @PutMapping
    public User putUser(@Valid @RequestBody User user) {
        ageCheck(user);
        loginCheck(user);
        User userOkName = emptyNameCheck(user);

        if (!users.containsKey(userOkName.getId())){
            log.info("Обновление не существующей задачи {}", userOkName.toString());
            throw new ValidationException ("Обновление не существующей задачи");
        }

        users.put(userOkName.getId(), userOkName);
        log.info("PUT /users. Количество пользователей: {}", users.size());
        return userOkName;
    }


    private void ageCheck(@Valid @RequestBody User user) {
        LocalDate currentDay = LocalDate.now();
        if (currentDay.isBefore(user.getBirthday())) {
            log.info("Не корректная дата рождения. Введено {}", user.getBirthday());
            throw new ValidationException ("Указана не корректная дата рождения");
        }
    }

    private void loginCheck(@Valid @RequestBody User user) {
        String login = user.getLogin();
        if (login.contains(" ")) {
            log.info("Не корректный логин. Введено {}", user.getLogin());
            throw new ValidationException ("Указан не корректный логин");
        }
    }

    private User emptyNameCheck(@Valid @RequestBody User user) {
        String name = user.getName();
        if (name.isBlank() || name.equals(null)) {
            user.setName(user.getLogin());
        }
        return user;
    }


}