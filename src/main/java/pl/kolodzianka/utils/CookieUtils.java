package pl.kolodzianka.utils;

import pl.kolodzianka.entities.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Optional;

public class CookieUtils {


    public static void createCookie(HttpServletResponse resp, User user) {
        Cookie loginCookie = new Cookie("zalogowany", user.getUsername());
        loginCookie.setMaxAge(30 * 60);
        resp.addCookie(loginCookie);
    }

    public static Optional<String> readCookie(String key, HttpServletRequest req) {
        return Arrays.stream(req.getCookies())
                .filter(c -> key.equals(c.getName()))
                .map(Cookie::getValue)
                .findAny();
    }

    public static void clearCookie(String key, HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookie = req.getCookies();
        for (Cookie outcookie : cookie) {
            if (outcookie != null && outcookie.getName().equals(key)) {
                outcookie.setMaxAge(0);
                resp.addCookie(outcookie);
            }
        }
    }
}
