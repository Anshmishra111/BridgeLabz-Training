package com.firstservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("user");
        String password = request.getParameter("pwd");

        // =========================
        // UC3 - NAME VALIDATION
        // =========================

        boolean validName = username != null
                && username.length() >= 3
                && Character.isUpperCase(username.charAt(0));

        if (!validName) {

            response.setContentType("text/html");

            response.getWriter().println(
                    "<h3 style='color:red;'>Invalid Name!</h3>"
            );

            response.getWriter().println(
                    "<p>Name must start with a capital letter "
                    + "and contain at least 3 characters.</p>"
            );

            response.getWriter().println(
                    "<a href='login.html'>Try Again</a>"
            );

            return;
        }

        // =========================
        // UC4 - PASSWORD VALIDATION
        // =========================

        boolean validPassword = isValidPassword(password);

        if (!validPassword) {

            response.setContentType("text/html");

            response.getWriter().println(
                    "<h3 style='color:red;'>Invalid Password!</h3>"
            );

            response.getWriter().println(
                    "<p>Password must:</p>"
            );

            response.getWriter().println(
                    "<ul>"
                    + "<li>Have minimum 8 characters</li>"
                    + "<li>Have at least 1 uppercase letter</li>"
                    + "<li>Have at least 1 numeric character</li>"
                    + "<li>Have exactly 1 special character</li>"
                    + "</ul>"
            );

            response.getWriter().println(
                    "<a href='login.html'>Try Again</a>"
            );

            return;
        }

        // =========================
        // LOGIN CHECK
        // =========================

        String predefinedUser = "Admin";
        String predefinedPassword = "Admin123@";

        if (predefinedUser.equals(username)
                && predefinedPassword.equals(password)) {

            request.setAttribute("user", username);

            request.getRequestDispatcher("LoginSuccess.jsp")
                    .forward(request, response);

        } else {

            response.setContentType("text/html");

            response.getWriter().println(
                    "<h3 style='color:red;'>"
                    + "Username or password is wrong."
                    + "</h3>"
            );

            response.getWriter().println(
                    "<a href='login.html'>Try Again</a>"
            );
        }
    }


    // UC4 Password Validation Method
    private boolean isValidPassword(String password) {

        if (password == null || password.length() < 8) {
            return false;
        }

        int uppercaseCount = 0;
        int digitCount     = 0;
        int specialCount   = 0;

        for (char ch : password.toCharArray()) {

            if (Character.isUpperCase(ch)) {
                uppercaseCount++;
            }
            else if (Character.isDigit(ch)) {
                digitCount++;
            }
            else if (!Character.isLetterOrDigit(ch)) {
                specialCount++;
            }
        }

        // exactly 1 special character (not "at least 1")
        return uppercaseCount >= 1
                && digitCount >= 1
                && specialCount == 1;
    }
}
