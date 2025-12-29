Nebula – Java MVC Student Skill Platform

Nebula is a Java-based web application built using the MVC architecture to help students register, log in, and manage their learning journey.  
This project was built to gain hands-on experience with backend development using Java web technologies.

---

## Features Implemented (Phase-1)

- User Registration (GET & POST)
- User Login with credential validation
- Session management
- Protected Dashboard
- Logout functionality
- Proper MVC architecture
- JSP pages secured under `WEB-INF`
- Context-path–safe navigation
- Git version control with GitHub

---

## Architecture (MVC)

- **Model**
  - `User.java`
  - `UserDAO.java`
  - `DBUtil.java`

- **View**
  - JSP pages (`login.jsp`, `register.jsp`, `dashboard.jsp`)
  - Stored under `WEB-INF/views`

- **Controller**
  - `HomeServlet`
  - `RegisterServlet`
  - `LoginServlet`
  - `LogoutServlet`

All navigation is handled through servlets (controllers), following proper MVC design.

---

## Tech Stack

- Java (Servlets & JSP)
- JDBC
- MySQL
- Apache Tomcat 9
- Maven
- Git & GitHub

---

## Authentication Flow

1. User opens the application
2. Login page is shown
3. New users can register
4. On successful login, session is created
5. Dashboard is accessible only when logged in
6. Logout invalidates the session

---

## How to Run the Project Locally

1. Clone the repository
   ```bash
   git clone https://github.com/<your-username>/nebula-java-mvc.git
2. Import the project into IntelliJ IDEA

3. Configure Apache Tomcat (version 9)

4. Set up MySQL database:

⦁	Create database nebula

⦁	Create required tables (users)

5. Update database credentials in DBUtil.java

6. Run the project on Tomcat and open:

   http://localhost:8080/nebula_war_exploded/

