<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Nebula | Dashboard</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>

    <div class="container">
        <nav class="sidebar">
            <div class="logo">
                <img src="${pageContext.request.contextPath}/assets/images/logo1.png">
                <h2>Nebula</h2>
                <p>Shaping Skills. Guiding Futures.</p>
            </div>
            <ul>
                <li class="active">Dashboard</li>
                <li>Recommendations</li>
                <li>Profile</li>
                <li>Logout</li>
            </ul>
        </nav>

        <main class="main-content">
            <header>
                <h1>Dashboard</h1>
                <p>You are logged in as student</p>
            </header>

            <div class="stats-container">
                <div class="card">
                    <h3>Total Skills</h3>
                    <div class="number">2</div>
                </div>
                <div class="card">
                    <h3>In Progress</h3>
                    <div class="number">1</div>
                </div>
                <div class="card">
                    <h3>Completed</h3>
                    <div class="number">0</div>
                </div>
            </div>

            <section class="skills-section">
                <h2>My Skills</h2>
                <div class="table-container">
                    <table>
                        <thead>
                            <tr>
                                <th>Skill</th>
                                <th>Category</th>
                                <th>Level</th>
                                <th>Status</th>
                                <th>Actions</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>Java</td>
                                <td>Programming</td>
                                <td>Beginner</td>
                                <td><span class="status-badge progress">In Progress</span></td>
                                <td>
                                    <button class="btn start">Start</button>
                                    <button class="btn complete">Complete</button>
                                    <button class="btn remove">Remove</button>
                                </td>
                            </tr>
                            <tr>
                                <td>DSA</td>
                                <td>Programming</td>
                                <td>Beginner</td>
                                <td><span class="status-badge not-started">Not Started</span></td>
                                <td>
                                    <button class="btn start">Start</button>
                                    <button class="btn complete">Complete</button>
                                    <button class="btn remove">Remove</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </section>
        </main>
    </div>
</body>
</html> CSS :root {
    --primary-blue: #2c3e50;
    --accent-blue: #3498db;
    --bg-light: #f0f4f8;
    --white: #ffffff;
    --text-dark: #333;
}

body {
    margin: 0;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    background: linear-gradient(135deg, #e0eafc 0%, #cfdef3 100%);
    color: var(--text-dark);
}

.container {
    display: flex;
    min-height: 100vh;
}

/* Sidebar Styling */
.sidebar {
    color: white;
    width: 30%;
    background: var(--white);
    padding: 20px;
    border-right: 1px solid #ddd;

        height:40px;
        display: flex;
        flex-direction: row;          /* 🔑 force horizontal */
        justify-content: space-between;
        align-items: center;          /* 🔑 vertical alignment */
        padding: 12px 24px;
        box-shadow: 0 2px 6px rgba(0,0,0,0.15);
}

.logo h2 { margin-bottom: 5px; color: var(--primary-blue); }
.logo p { font-size: 12px; color: #777; margin-bottom: 30px; }

.sidebar ul { list-style: none; padding: 0; }
.sidebar li {

    padding: 12px 15px;
    margin: 8px 0;
    border-radius: 8px;
    cursor: pointer;
    transition: 0.3s;
}

.sidebar li.active, .sidebar li:hover {
    background: var(--primary-blue);
    color: white;
}

/* Main Content Area */
.main-content {
    flex: 1;
    padding: 40px;
}

/* Cards Styling */
.stats-container {
    display: flex;
    gap: 20px;
    margin-top: 20px;
}

.card {
    background: var(--white);
    flex: 1;
    padding: 25px;
    border-radius: 15px;
    box-shadow: 0 4px 15px rgba(0,0,0,0.05);
}

.card h3 { font-size: 16px; color: #666; }
.card .number { font-size: 32px; font-weight: bold; margin-top: 10px; }

/* Table Styling */
.table-container {
    background: var(--white);
    border-radius: 15px;
    padding: 20px;
    margin-top: 20px;
    box-shadow: 0 4px 15px rgba(0,0,0,0.05);
}

table { width: 100%; border-collapse: collapse; }
th { text-align: left; padding: 15px; border-bottom: 2px solid var(--bg-light); color: #888; }
td { padding: 15px; border-bottom: 1px solid var(--bg-light); }

/* Badges & Buttons */
.status-badge {
    padding: 5px 12px;
    border-radius: 20px;
    font-size: 12px;
}
.progress { background: #fff3cd; color: #856404; }
.not-started { background: #e2e3e5; color: #383d41; }

.btn {
    border: none;
    padding: 8px 12px;
    border-radius: 6px;
    cursor: pointer;
    margin-right: 5px;
}
.start { background: var(--primary-blue); color: green; }
.complete { background: #27ae60; color: blue; }
.remove { background: #e74c3c; color: red; }
<!DOCTYPE html>
<html lang="en"> * {
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}

body {
    font-family: 'Segoe UI', sans-serif;
    /* Deep Navy Background to match mockup */
    background-color: #1a2a40;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
}

.login-card {
    background: white;
    width: 100%;
    max-width: 400px;
    padding: 40px;
    border-radius: 12px;
    box-shadow: 0 10px 25px rgba(0,0,0,0.3);
    text-align: center;
}

.login-header {
    margin-bottom: 30px;
}

.brand-name {
    color: #1a2a40;
    font-size: 28px;
    font-weight: 700;
}

.tagline {
    font-size: 12px;
    color: #666;
}

h2 {
    margin-bottom: 20px;
    color: #333;
    font-weight: 500;
}

.input-group {
    text-align: left;
    margin-bottom: 20px;
}

.input-group label {
    display: block;
    font-size: 14px;
    margin-bottom: 8px;
    color: #555;
}

.input-group input {
    width: 100%;
    padding: 12px;
    border: 1px solid #ddd;
    border-radius: 6px;
    font-size: 14px;
}

.login-btn {
    width: 100%;
    padding: 12px;
    background-color: #2c5282; /* Professional Blue */
    color: white;
    border: none;
    border-radius: 6px;
    font-size: 16px;
    cursor: pointer;
    transition: background 0.3s;
}

.login-btn:hover {
    background-color: #1a365d;
}

.footer-links {
    margin-top: 20px;
    font-size: 14px;
}

.footer-links a {
    color: #2c5282;
    text-decoration: none;
    font-weight: bold;
}
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Nebula | Login</title>
    <link rel="stylesheet" href="login-style.css">
</head>
<body>
    <div class="login-page">
        <div class="login-card">
            <div class="login-header">
                <img src="your-logo1.png" alt="Nebula Logo" class="logo-img">
                <h1 class="brand-name">Nebula</h1>
                <p class="tagline">Shaping Skills. Guiding Futures.</p>
            </div>

            <h2>Login</h2>

            <form>
                <div class="input-group">
                    <label>Email:</label>
                    <input type="email" placeholder="Enter your email" required>
                </div>

                <div class="input-group">
                    <label>Password:</label>
                    <input type="password" placeholder="Enter your password" required>
                </div>

                <button type="submit" class="login-btn">Login</button>
            </form>

            <div class="footer-links">
                <p>New user? <a href="#">Register here</a></p>
            </div>
        </div>
    </div>
</body>
</html>