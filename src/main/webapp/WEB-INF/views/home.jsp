<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Nebula | Home</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/style.css">
</head>
<body class="public">

<%@ include file="/WEB-INF/views/components/public-header.jsp" %>

<section class="hero">
    <h1>Nebula</h1>
    <p class="tagline">Where Learning Finds Direction</p>

    <div class="hero-actions">
        <a href="${pageContext.request.contextPath}/login" class="btn">Login</a>
        <a href="${pageContext.request.contextPath}/register" class="btn-outline">Register</a>
    </div>
</section>

<section class="about">
    <h2>About Nebula</h2>
    <p>
        Nebula helps students discover the right skills, build structured learning
        roadmaps, and track progress with clarity and confidence.
    </p>
</section>

<%@ include file="/WEB-INF/views/components/footer.jsp" %>

</body>
</html>
