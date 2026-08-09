<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%-- Redirect root URL to /greetings/ --%>
<% response.sendRedirect(request.getContextPath() + "/greetings/"); %>
