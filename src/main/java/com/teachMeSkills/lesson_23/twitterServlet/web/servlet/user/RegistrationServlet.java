package com.teachMeSkills.lesson_23.twitterServlet.web.servlet.user;

import com.teachMeSkills.lesson_23.twitterServlet.entity.Role;
import com.teachMeSkills.lesson_23.twitterServlet.entity.User;
import com.teachMeSkills.lesson_23.twitterServlet.service.UserService;
import org.omg.PortableInterceptor.USER_EXCEPTION;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/registration")
public class RegistrationServlet extends HttpServlet {

    private final UserService userService = new UserService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int userId = userService.getIdUser();
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        User user = new User();
        user.setIdUser(userId);
        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);

        if (role.contains("admin")){
            user.setRole(Role.ADMIN);
        } else {
            user.setRole(Role.USER);
        }

        boolean isAdded = userService.addUser(user);
        if (isAdded) {
            resp.getWriter().println("Ok");
        } else {
            resp.getWriter().println("Error");
            resp.setStatus(400);
        }

    }
}
