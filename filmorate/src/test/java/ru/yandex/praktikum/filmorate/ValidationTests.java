
package ru.yandex.praktikum.filmorate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.yandex.praktikum.filmorate.model.Film;
import ru.yandex.praktikum.filmorate.model.User;
import ru.yandex.praktikum.filmorate.validation.Validator;

public class ValidationTests {

    private static Validator validator;

    private static User normalUser;
    private static User userWithEmptyEmail;
    private static User userWithEmailWithoutAt;
    private static User userWithEmptyLogin;
    private static User userLoginWithSpaces;
    private static User birthdayInFuture;

    private static Film normalFilm;
    private static Film filmWithEmptyName;
    private static Film filmWithDescriptionCharactersExceeding200;
    private static Film filmReleasedEarlierThanFilmographyEra;
    private static Film filmWithDurationZero;

    // Блок инициализации тестовых объектов
    @BeforeAll
    public static void init() {
        validator = new Validator();

        normalUser = new User( 1, "a@yandex.ru", "aaa", "aaa", "1995-05-30");
        emptyEmailUser = new User( 2, "", "bbb", "bbb", "1995-05-30");
        emailWithoutAt = new User( 3, "ccc/yandex.ru", "ccc", "ccc", "1995-05-30");
        emptyLoginUser = new User( 4, "ddd@yandex.ru", "", "ddd", "1995-05-30");
        loginWithSpacesUser = new User( 5, "eee@yandex.ru", "eee  eee eee", "eee", "1995-05-30");
        futureUser = new User( 6, "fff@yandex.ru", "fff", "fff", "2032-05-30");

        normalFilm = new Film(1, aaaa", "aaaa", "1967-03-25", 100);
        filmWithEmptyName = new Film(2, "", "bbbbb", "1967-03-25", 100);
        filmWithDescriptionCharactersExceeding200 =
                new Film(3, "cccccc",
                "ccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccccc", "1967-03-25", 100);
        filmReleasedEarlierThanFilmographyEra = new Film(4, "dddd", "dddd",
                "1895-12-27", 100);
        filmWithDurationZero = new Film(5, "eeeeee", "eeeeee",
                "1995-12-27", 0);
    }

    @DisplayName("Valid user")
    @Test
    public void checkValidUser() {
        Assertions.assertTrue(validator.validateRequestBody(normal));
    }

    @DisplayName("Valid film")
    @Test
    public void checkValidFilm() {
        Assertions.assertTrue(validator.validateRequestBody(normalFilm));
    }

    @DisplayName("User with empty email")
    @Test
    public void checkUserWithEmptyEmail() {
        Assertions.assertFalse(validator.validateRequestBody(emptyEmailUser));
    }

    @DisplayName("User with email without @")
    @Test
    public void checkUserWithEmailWithoutAt() {
        Assertions.assertFalse(validator.validateRequestBody(emailWithoutAt));
    }

    @DisplayName("User with empty login")
    @Test
    public void checkUserWithEmptyLogin() {
        Assertions.assertFalse(validator.validateRequestBody(emptyLoginUser));
    }

    @DisplayName("User login with spaces")
    @Test
    public void checkUserLoginWithSpaces() {
        Assertions.assertFalse(validator.validateRequestBody(loginWithSpacesUser));
    }

    @DisplayName("User birthday in future")
    @Test
    public void checkUserBirthdayInFuture() {
        Assertions.assertFalse(validator.validateRequestBody(futureUser));
    }

    @DisplayName("Film with empty name")
    @Test
    public void checkFilmWithEmptyName() {
        Assertions.assertFalse(validator.validateRequestBody(filmWithEmptyName));
    }

    @DisplayName("Film description exceeding 200 characters")
    @Test
    public void checkFilmWithDescriptionCharactersExceeding200() {
        Assertions.assertFalse(validator.validateRequestBody(filmWithDescriptionCharactersExceeding200));
    }

    @DisplayName("Film released earlier than filmography era")
    @Test
    public void checkFilmReleasedEarlierThanFilmographyEr() {
        Assertions.assertFalse(validator.validateRequestBody(filmReleasedEarlierThanFilmographyEra));
    }

    @DisplayName("Film with 0 duration")
    @Test
    public void checkFilmWithDurationZero() {
        Assertions.assertFalse(validator.validateRequestBody(filmWithDurationZero));
    }
}