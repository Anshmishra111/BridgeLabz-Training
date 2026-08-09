<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Greeting #${greeting.id} — My Greetings App</title>
    <meta name="description" content="Edit and update an existing greeting.">
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
        <a href="${pageContext.request.contextPath}/greetings/" class="page-back-btn">
            ← All Greetings
        </a>
    </div>

    <!-- Form Card -->
    <div class="form-card">

        <h1 class="form-title">✏️ Edit Greeting <span style="color:var(--text-muted);font-size:1rem;">#${greeting.id}</span></h1>
        <p class="form-subtitle">Modify the greeting details below and click Update to save your changes.</p>

        <!-- Validation Error -->
        <c:if test="${not empty errorMsg}">
            <div class="flash flash-error">⚠️ ${errorMsg}</div>
        </c:if>

        <!--
            POST /greetings/edit
            Hidden 'id' field carries the greeting ID back to the controller.
            @ModelAttribute Greeting binds id, name, and message.
        -->
        <form action="${pageContext.request.contextPath}/greetings/edit"
              method="post"
              id="editGreetingForm"
              novalidate>

            <!-- Hidden ID — must be sent back so controller knows which record to update -->
            <input type="hidden" name="id" value="${greeting.id}">

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
                    placeholder="Type your heartfelt greeting here..."
                    maxlength="500"
                    required>${greeting.message}</textarea>
            </div>

            <!-- Original creation timestamp (read-only info) -->
            <c:if test="${not empty greeting.formattedDate}">
                <p style="font-size:0.78rem; color:var(--text-muted); margin-bottom:20px;">
                    📅 Originally created: ${greeting.formattedDate}
                </p>
            </c:if>

            <!-- Form Actions -->
            <div class="form-actions">
                <button type="submit" class="btn btn-primary btn-lg" id="updateBtn">
                    💾 Update Greeting
                </button>
                <a href="${pageContext.request.contextPath}/greetings/view?id=${greeting.id}"
                   class="btn btn-ghost">View</a>
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
