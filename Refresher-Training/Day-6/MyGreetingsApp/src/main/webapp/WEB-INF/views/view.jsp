<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Greeting from ${greeting.name} — My Greetings App</title>
    <meta name="description" content="View the greeting from ${greeting.name}: ${greeting.message}">
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
                ← All Greetings
            </a>
        </div>
    </nav>
</header>

<!-- ═══════════════ MAIN CONTENT ══════════════════════════════════════ -->
<main class="page-wrapper">

    <!-- Back link -->
    <div class="page-header">
        <a href="${pageContext.request.contextPath}/greetings/" class="page-back-btn">
            ← All Greetings
        </a>
    </div>

    <!-- Detail Card -->
    <div class="detail-card">

        <!-- Card Header — gradient with avatar and name -->
        <div class="detail-card-header">
            <div class="detail-avatar">
                ${greeting.name.substring(0,1)}
            </div>
            <div class="detail-name">${greeting.name}</div>
            <div class="detail-meta">Greeting #${greeting.id} &nbsp;·&nbsp; 🕒 ${greeting.formattedDate}</div>
        </div>

        <!-- Card Body — message and actions -->
        <div class="detail-card-body">

            <div class="detail-label">💬 Message</div>
            <blockquote class="detail-message">
                ${greeting.message}
            </blockquote>

            <!-- Action Buttons -->
            <div class="detail-actions">
                <a href="${pageContext.request.contextPath}/greetings/edit?id=${greeting.id}"
                   class="btn btn-warning" id="editBtn">
                    ✏️ Edit
                </a>
                <a href="${pageContext.request.contextPath}/greetings/delete?id=${greeting.id}"
                   class="btn btn-danger"
                   id="deleteBtn"
                   onclick="return confirm('Are you sure you want to delete this greeting from ${greeting.name}?')">
                    🗑 Delete
                </a>
                <a href="${pageContext.request.contextPath}/greetings/"
                   class="btn btn-ghost" id="backBtn">
                    ← Back
                </a>
            </div>

        </div>
    </div>

</main>

<!-- ═══════════════ FOOTER ════════════════════════════════════════════ -->
<footer class="site-footer">
    <p>My Greetings App &mdash; Built with <strong>Spring MVC 5.3</strong> &amp; Tomcat</p>
</footer>

</body>
</html>
