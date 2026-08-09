<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Greeting — My Greetings App</title>
    <meta name="description" content="Create a new greeting to add to your greetings board.">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>

<!-- ═══════════════ HEADER / NAV ═══════════════════════════════════════ -->
<header class="site-header">
    <nav class="nav-inner">
        <a href="${pageContext.request.contextPath}/greetings/" class="nav-brand">
            <div class="nav-brand-icon">✉️</div>
            <span class="nav-brand-text">My<span>Greetings</span></span>
        </a>
        <div class="nav-actions">
            <a href="${pageContext.request.contextPath}/greetings/" class="btn btn-ghost btn-sm">
                ← Back to Home
            </a>
        </div>
    </nav>
</header>

<!-- ═══════════════ MAIN CONTENT ══════════════════════════════════════ -->
<main class="page-wrapper">

    <!-- Page header -->
    <div class="page-header">
        <a href="${pageContext.request.contextPath}/greetings/" class="page-back-btn">← All Greetings</a>
    </div>

    <!-- Form Card -->
    <div class="form-card">

        <h1 class="form-title">✉️ New Greeting</h1>
        <p class="form-subtitle">Fill in the details below to add a warm greeting to the board.</p>

        <!-- Validation Error -->
        <c:if test="${not empty errorMsg}">
            <div class="flash flash-error">⚠️ ${errorMsg}</div>
        </c:if>

        <!--
            POST /greetings/add
            @ModelAttribute Greeting binds name and message fields
        -->
        <form action="${pageContext.request.contextPath}/greetings/add"
              method="post"
              id="addGreetingForm"
              novalidate>

            <!-- Sender Name -->
            <div class="form-group">
                <label class="form-label" for="name">Sender Name</label>
                <input
                    type="text"
                    id="name"
                    name="name"
                    class="form-control"
                    placeholder="e.g. Alice Johnson"
                    value="${greeting.name}"
                    maxlength="80"
                    autofocus
                    required>
            </div>

            <!-- Greeting Message -->
            <div class="form-group">
                <label class="form-label" for="message">Greeting Message</label>
                <textarea
                    id="message"
                    name="message"
                    class="form-control"
                    placeholder="Type your heartfelt greeting here... e.g. Good morning! Wishing you a fantastic day!"
                    maxlength="500"
                    required>${greeting.message}</textarea>
            </div>

            <!-- Form Actions -->
            <div class="form-actions">
                <button type="submit" class="btn btn-primary btn-lg" id="saveBtn">
                    ✅ Save Greeting
                </button>
                <a href="${pageContext.request.contextPath}/greetings/"
                   class="btn btn-ghost">Cancel</a>
            </div>

        </form>
    </div>

</main>

<!-- ═══════════════ FOOTER ════════════════════════════════════════════ -->
<footer class="site-footer">
    <p>My Greetings App &mdash; Built with <strong>Spring MVC 5.3</strong> &amp; Tomcat</p>
</footer>

</body>
</html>
