<h1>FinanceTracker Application</h1>

This is a Spring Boot-based FinanceTracker web application designed for personal finance management. It includes basic functionality for user login, registration, and a dashboard. While the login and registration system works seamlessly, the integration between the database and some key features like the dashboard and categories pages is still in development due to issues with Thymeleaf.
Features

    Login and Registration System: Fully functional and mapped pages for user authentication.
        /finance/login - Login page.
        /finance/register - Registration page.
        /finance/index - Public landing page.
        /finance/dashboard - Secured dashboard page after login (Database connection pending).
        /finance/logout - Logout functionality.
    Dashboard: Once logged in, users can see the available sections such as total income, total expenses, and categories, though these are not yet connected to the database.
    Categories, Total Income & Expenses: These sections are visible on the dashboard, but data isn't being fetched from the database due to Thymeleaf issues.
    CSRF Protection: Secured with CookieCsrfTokenRepository for protection against CSRF attacks.
    Session Management: Users are redirected to their dashboard after a successful login.

<h2>Known Limitations</h2>

    Database Integration (In Progress): The database is not yet fully connected to the dashboard and categories pages. There are ongoing issues with Thymeleaf, which are being worked on.
    Categories and Financial Data: While categories, total income, and total expenses are visible, these are static at the moment and do not fetch data from the database. This feature is under development.

<h2>Requirements</h2>

    Java 17+: Make sure you have Java 17 or higher installed.
    Maven: Required to manage dependencies.
    MySQL: The application uses MySQL as its database. If you do not have MySQL installed, you can enable it using XAMPP.

Install and Configure MySQL (via XAMPP)

    Download and install XAMPP.
    Start XAMPP and ensure MySQL is running in the Control Panel.
    Create a new MySQL database using phpMyAdmin.

Configure Application Properties

In the src/main/resources/application.properties file, you need to specify the correct database settings.

Here is a sample configuration for MySQL:

properties

# Application Properties for FinanceTracker

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/<your-database-name>
spring.datasource.username=root
spring.datasource.password=
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

Note: Replace <your-database-name> with the actual name of the database you created in MySQL.
Running the Application

    Clone the repository:

    bash

git clone https://github.com/your-username/FinanceTracker.git

Navigate to the project directory:

bash

cd FinanceTracker

Run the application using Maven:

bash

    mvn spring-boot:run

Accessing the Application

Once the application is running, you can access the following URLs:

    Login Page: http://localhost:8080/finance/login
    Registration Page: http://localhost:8080/finance/register
    Dashboard: http://localhost:8080/finance/dashboard (Still in development)

Dependencies

    Spring Boot: Main framework for the application.
    Spring Security: Handles authentication and authorization.
    MySQL Connector: Required for database connectivity.
    Thymeleaf: Template engine used for rendering HTML views.

Development Notes

    Password Encryption: The application is using NoOpPasswordEncoder for development purposes, which stores passwords as plain text. This should not be used in a production environment.
    Thymeleaf Errors: Current issues with Thymeleaf have prevented full database integration for categories and dashboard features.

Future Improvements

    Resolve Thymeleaf issues and fully connect the database to the dashboard and category pages.
    Implement user-specific financial tracking (e.g., income, expenses, and category management).
    Improve registration validation and add more robust security mechanisms.

Contributing

Feel free to fork this project, report any issues, or contribute via pull requests. Contributions are welcome!

For any questions, suggestions, or feedback, feel free to reach out or open an issue.

Happy coding! 🚀
