package com.firstservlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * FirstServlet — UC1
 *
 * Mapped to /FirstServlet via the @WebServlet annotation.
 * No web.xml entry needed.
 *
 * URL: http://localhost:8080/FirstServletProject/FirstServlet
 *
 * Internal flow:
 *  Browser → HTTP GET → Tomcat → Web Container → FirstServlet → doGet()
 *         → HttpServletResponse → HTML → Browser
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Tell the browser we are sending back HTML
        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<body>");
        out.println("<h1>Hello World! My First Servlet</h1>");
        out.println("</body>");
        out.println("</html>");

        out.close();
    }
}
