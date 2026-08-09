<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Greetings App — All Greetings</title>
    <meta name="description" content="Spring MVC Greetings App — Create, Read, Update and Delete your greetings.">
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
            <a href="${pageContext.request.contextPath}/greetings/add" class="btn btn-primary btn-sm">
                ＋ New Greeting
            </a>
        </div>
    </nav>
</header>

<!-- ═══════════════ MAIN CONTENT ══════════════════════════════════════ -->
<main class="page-wrapper">

    <!-- Hero Section -->
    <section class="hero">
        <div class="hero-badge">✨ Spring MVC CRUD App</div>
        <h1>Your Greetings Board</h1>
        <p>Send warm wishes, share kind words, and spread positivity — one greeting at a time.</p>

        <!-- Stats -->
        <div class="stats-bar">
            📬 <strong>${totalCount}</strong> greeting<c:if test="${totalCount != 1}">s</c:if> saved
        </div>

        <a href="${pageContext.request.contextPath}/greetings/add" class="btn btn-accent btn-lg">
            ＋ Add New Greeting
        </a>
    </section>

    <!-- Flash Messages -->
    <c:if test="${not empty successMsg}">
        <div class="flash flash-success">✅ ${successMsg}</div>
    </c:if>
    <c:if test="${not empty errorMsg}">
        <div class="flash flash-error">⚠️ ${errorMsg}</div>
    </c:if>

    <!-- Greetings Grid -->
    <c:choose>
        <c:when test="${empty greetings}">
            <div class="empty-state">
                <div class="empty-state-icon">💌</div>
                <h3>No greetings yet!</h3>
                <p>Be the first to add a greeting and spread some joy.</p>
                <br>
                <a href="${pageContext.request.contextPath}/greetings/add" class="btn btn-primary">
                    ＋ Add First Greeting
                </a>
            </div>
        </c:when>
        <c:otherwise>
            <div class="cards-grid">
                <c:forEach var="g" items="${greetings}">
                    <div class="greeting-card">

                        <div class="card-header">
                            <!-- Avatar: first letter of sender's name -->
                            <div class="card-avatar">
                                ${g.name.substring(0,1)}
                            </div>
                            <div style="flex:1">
                                <div class="card-name">${g.name}</div>
                                <div class="card-date">🕒 ${g.formattedDate}</div>
                            </div>
                            <span class="card-id-badge">#${g.id}</span>
                        </div>

                        <!-- Message preview (3 lines) -->
                        <p class="card-message">"${g.message}"</p>

                        <!-- Action Buttons -->
                        <div class="card-actions">
                            <a href="${pageContext.request.contextPath}/greetings/view?id=${g.id}"
                               class="btn btn-ghost btn-sm">👁 View</a>
                            <a href="${pageContext.request.contextPath}/greetings/edit?id=${g.id}"
                               class="btn btn-warning btn-sm">✏️ Edit</a>
                            <a href="${pageContext.request.contextPath}/greetings/delete?id=${g.id}"
                               class="btn btn-danger btn-sm"
                               onclick="return confirm('Delete this greeting from ${g.name}?')">🗑 Delete</a>
                        </div>

                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>

</main>

<!-- ═══════════════ FOOTER ════════════════════════════════════════════ -->
<footer class="site-footer">
    <p>My Greetings App &mdash; Built with <strong>Spring MVC 5.3</strong> &amp; Tomcat &nbsp;|&nbsp;
    <a href="${pageContext.request.contextPath}/greetings/add">Add Greeting</a></p>
</footer>

</body>
</html>
