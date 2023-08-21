# THINKR Learning Management System 

Computer Programming and Information Systems Software Engineering Capstone 

(FSC-CSC-325: Capstone Project) 

## Summary

The THINKR Learning Management System is a comprehensive tool designed to streamline and enhance the educational experience for both students and professors alike. Born out of a capstone project for a software engineering class, THINKR was meticulously designed by a team of four dedicated individuals, with myself taking on the dual role of project manager and scrum master. This project encapsulates the core tenets of the Software Development Life Cycle (SDLC), utilizing Scrum meetings, sprints, retrospectives, and other agile development practices to ensure a polished final product. Like traditional Learning Management Systems, THINKR is intended for academic institutions, serving as a centralized hub to manage classes, assignments, announcements, and other academic requirements. The THINKR LMS is more than just a tool; it's a holistic educational experience, bridging the gap between educators and learners in an ever-evolving digital landscape.


## Assignment Context


## Features
- **Login/Register**: Shared access through an animated greeting window for user authentication.
- **Main Window**: Accessible post-login. Contains buttons for home, grades, calendar, and logout functionalities.
- **Home Page**: Displays a calendar preview, offers password reset, allows course list viewing, and navigation to course pages.
- **Course Page**: Access for viewing and creating announcements, viewing the list of assignments, and navigating to individual assignment pages.
- **Assignment Page**: Enables viewing and creation of assignment details, submission uploads, and grade entry.
- **Grades Page**: Provides a comprehensive view of grades, facilitates GPA calculation, and offers navigation to the assignment page.
- **Calendar Page**: A visual interface to view assignment and exam dates.
- **Logout**: Securely signs out the user and returns them to the greeting window.

## Users
- **Student**: Can view courses, submit assignments, check grades, and view announcements.
- **Professor**: Can manage courses, create assignments, grade submissions, and post announcements.
- **Admin**: Has overarching control over user accounts and course creation.

## How it Works


### Login/Register Window

Upon launching the application, users encounter an animated greeting window where they can either login with existing credentials or register as a new user.

This window has various UI elements like buttons, text fields, password fields, combo boxes, and animated panes.

The initialize method sets up the UI components when the FXML is loaded, particularly the account type ComboBox for account registration with its options: "STUDENT", "PROFESSOR", and "ADMIN".

There are methods **showExisting** and **showNew** that provide animated transitions between the login and registration screens using fade transitions and path transitions.

The **handleButton_login** method validates user input against registered users in the Firestore database (App.fstore). If a match is found, it initializes the current user (App.currentUser) and navigates to the appropriate home page based on the user type: Admin, Student, or Professor.

The **handleButton_register** method collects user input for registration, validates it, and if successful, adds a new user to the Firestore database. If the user is successfully registered, a success message is shown, and the view transitions back to the login screen.

Throughout, there are checks and balances to ensure proper user input. For instance, matching emails and passwords, non-empty input fields, and valid dates for the date of birth.


### Program UI/UX, Main Window & Home Page

Post-authentication, the main window appears with options to navigate to the home page, view grades, access the calendar, or log out.

This larger window uses several UI elements like buttons, text fields, table columns, and VBoxes.

The initialize method is responsible for setting up the interface when loaded. This starts by setting up the navigation menu with the **updateMenu** method which dynamically adjusts the navigation menu based on the type of user and only displays the relevant options. This includes filling the courses table with the necessary data according to the user type.

This page also offers a mini calendar preview for a quick date reference. 

#### List of Courses Table

The **readCoursesIntoTable** method interacts with Firestore to read and write data to populate the table with courses relevant to the current user (i.e., courses they're enrolled in if a student or teaching if a professor) 
The **setOnMousePressed** method adds an event listener to the courses table such that when a course is double-clicked, it sets the current course and navigates the user to the detailed course view page.
The **listOfCourses** observable list keeps track of current user courses throughout the program.

#### Password Reset

The **handleButton_resetPassword** method allows users to reset their passwords. It checks if the entered passwords match and then updates the Firestore database.


### Course Page

Here, users can view or create announcements related to the course. 
A list of assignments is accessible, and each assignment can be clicked on to navigate to its dedicated page.


### Assignment Page
This page showcases the details of an assignment, where professors can create assignments and graed submissions, while students can upload files and details for their submissions.

This controller inherits from HomePageController, making it a specialized version tailored to handle assignment-specific actions. The window utilizes various UI elements like buttons, text fields, text areas, and boxes for efficient data entry.

The **buildAssignment** method is used to dynamically create the assignment view depending on which user type is viewing. This includes the title, due date, assignment details, and various action buttons. The screenshot displays a view meant for the professor when creating an assignment before any submissions.

The **createAssignment** method is responsible for creating a new assignment and adding it to the Firestore database. This includes aetting assignment metadata and creating a submission entry for every student in the current course. (Nongraded asignments are marked with a temporary grade of -1) This method uses **ZonedDateTime** to handle the conversion of the assignment's due date to a **Timestamp** which is suitable for Firestore storage.


### Grades Page
Users can view their grades for each assignment. 
Additionally, there's functionality to calculate the GPA. 
Each grade entry also allows navigation back to the respective assignment page for detailed viewing.


### Calendar Page
A visual representation of assignment and exam dates ensures users can plan their academic activities efficiently.


### Admin Page

This page provides administrative functionalities that allow the admin to manage user accounts. The admin can add, read, update, and delete user records from the Firestore database and see the changes reflected in the user table. The form fields are also dynamic, updating based on the selected user in the table.

The controller inherits from HomePageController, making it a specialized version tailored to handle administrative-specific actions. This page utilizes various UI elements like buttons, text fields, text areas, and boxes for efficient data entry.

The initialize method prepares the user interface when it's loaded. It sets up the combobox for user types, clears form fields, binds table columns to properties, and sets an event handler to update form fields when a user is selected from the table.

#### Event Handlers for CRUD Operations:
- **handle_writeRecord:**  Writes a new user record to the Firestore database.
- **handle_readRecords:**  Clears the form fields and re-reads all users from the Firestore database, displaying them in the table.
- **handle_updateRecord:** Updates the currently selected user record in the Firestore database.
- **handle_removeRecord:** Deletes the currently selected user record from the Firestore database.
- **handle_clearRecord:**  Clears the form fields and refreshes the user table.


### Logout
For security, users can log out of the system, which returns them to the initial greeting window.

