package pl.kolodzianka.utils;

import pl.kolodzianka.entities.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {


    public static void createCookie(HttpServletResponse resp, User user){
        Cookie loginCookie = new Cookie("zalogowany", user.getUsername());
        loginCookie.setMaxAge(30*60);
        resp.addCookie(loginCookie);
    }
}
