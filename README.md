# THINKR Learning Management System 

Computer Programming and Information Systems Software Engineering Capstone 

(FSC-CSC-325: Capstone Project) 



## Table of Contents

- [Summary](#summary)
- [Technologies](#technologies)
- [Setup](#setup)
  - [Prerequisites](#prerequisites)
  - [Instructions](#instructions)
- [Features](#features)
- [Intended Users](#intended-users)
- [How it Works](#how-it-works)
  - [Welcome Window (Login/Register)](#welcome-window-loginregister)
    - [Login](#login)
    - [Register](#register)
  - [Program UI/UX, Main Window & Home Page](#program-uiux-main-window--home-page)
  - [Course Page](#course-page)
  - [Assignment Page](#assignment-page)
  - [Grades Page](#grades-page)
  - [Calendar Page](#calendar-page)
  - [Admin Page](#admin-page)
  - [Logout](#logout)
- [Status](#status)
- [Credits](#credits)



## Summary

The THINKR Learning Management System is a comprehensive tool designed to streamline and enhance the educational experience for both students and professors alike. Born out of a capstone project for a software engineering class, THINKR was meticulously designed by a team of four dedicated individuals, with myself taking on the dual role of project manager and scrum master. This project encapsulates the core tenets of the Software Development Life Cycle (SDLC), utilizing Scrum meetings, sprints, retrospectives, and other agile development practices to ensure a polished final product. Like traditional Learning Management Systems, THINKR is intended for academic institutions, serving as a centralized hub to manage classes, assignments, announcements, and other academic requirements. The THINKR LMS is more than just a tool; it's a holistic educational experience, bridging the gap between educators and learners in an ever-evolving digital landscape.



## Technologies 

- **Apache Netbeans** primary IDE
- **Java** JDK version 11, backend programming language
- **Maven** dependency management tool
- **JavaFX** Controls, Swing, Graphics, FXML frontend markup language
- **MaterialFX** JavaFX enhanced modern UI components library
- **CalendarFX** UI library for interactive calendar view and interface 
- **Google Cloud Firestore** non-relational noSQL database
- **Firebase Admin SDK**  server-side cloud storage functionality



## Setup

### Prerequisites:

1. **Java Development Kit (JDK)**
   - Version: 11
   - [Download Link](https://adoptopenjdk.net/?variant=openjdk11)
   
2. **Apache Maven**
   - [Installation Guide](https://maven.apache.org/install.html)
   
3. **Firebase Admin SDK**
   - You'll need to set up a Firebase project and download the service account key. This will be a JSON file.
   - [Firebase Console](https://console.firebase.google.com/)
   - [Admin SDK Setup Guide](https://firebase.google.com/docs/admin/setup)

### Instructions:

1. **Clone the Repository**

2. **Firebase Configuration**:
- Place your Firebase service account key JSON file named `key.json` in the `resources/edu/farmingdale/csc325_project` directory.
- Please note: The project uses the `FirestoreContext.java` class to initialize the Firebase connection by fetching the Firebase service account key file. This class is configured to read the default filename of `key.json` and would need to be reconfigured accordingly for the use of any other filename.

3. **Build the Project**

4. **Run the Project**


## Features
- **Login/Register**: Shared access through an animated greeting window for user authentication.
- **Main Window**: Accessible post-login. Contains buttons for home, grades, calendar, and logout functionalities.
- **Home Page**: Displays a calendar preview, offers password reset, allows course list viewing, and navigation to course pages.
- **Course Page**: Access for viewing and creating announcements, viewing the list of assignments, and navigating to individual assignment pages.
- **Assignment Page**: Enables viewing and creation of assignment details, submission uploads, and grade entry.
- **Grades Page**: Provides a comprehensive view of grades, facilitates GPA calculation, and offers navigation to the assignment page.
- **Calendar Page**: A visual interface to view assignment and exam dates.
- **Logout**: Securely signs out the user and returns them to the greeting window.



## Intended Users
- **Student**: Can view courses, submit assignments, check grades, and view announcements.
- **Professor**: Can manage courses, create assignments, grade submissions, and post announcements.
- **Admin**: Has overarching control over user accounts and course creation.



## How it Works


### Welcome Window (Login/Register)

![animation](https://github.com/alexander-harmaty/THINKR-Learning-Management-System/assets/92049896/f838b817-4680-42e2-9746-74a59b3f6cc0)

Upon launching the application, users encounter an animated greeting window where they can either login with existing credentials or register as a new user.

This window has various UI elements like buttons, text fields, password fields, combo boxes, and animated panes.

The initialize method sets up the UI components when the FXML is loaded, particularly the account type ComboBox for account registration with its options: "STUDENT", "PROFESSOR", and "ADMIN".

There are methods **showExisting** and **showNew** that provide animated transitions between the login and registration screens using fade transitions and path transitions.

#### Login

![login](https://github.com/alexander-harmaty/THINKR-Learning-Management-System/assets/92049896/4a2ee9ea-be7c-4af3-876c-bcc83005c376)

The **handleButton_login** method validates user input against registered users in the Firestore database (App.fstore). If a match is found, it initializes the current user (App.currentUser) and navigates to the appropriate home page based on the user type: Admin, Student, or Professor.

#### Register

![register](https://github.com/alexander-harmaty/THINKR-Learning-Management-System/assets/92049896/f504ebf8-3866-44ed-b9bd-946e008f4372)

The **handleButton_register** method collects user input for registration, validates it, and if successful, adds a new user to the Firestore database. Throughout, there are checks to ensure proper user input. For instance, matching emails and passwords, non-empty input fields, and valid dates for the date of birth. If the user is successfully registered, a success message is shown, and the view transitions back to the login screen.


### Program UI/UX, Main Window & Home Page

![home](https://github.com/alexander-harmaty/THINKR-Learning-Management-System/assets/92049896/438710d8-4bfa-4209-b42c-ae2ca8e86464)

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

![course](https://github.com/alexander-harmaty/THINKR-Learning-Management-System/assets/92049896/4fa2b25b-158f-4bca-b91a-b355c25c8a62)

This page provides functionalities to display course-related information, like assignments and announcements, to both students and professors. For professors specifically, it enables the ability to create new announcements and assignments. The controller integrates with the Firestore database to fetch, add, and update course-related data.

The controller inherits from HomePageController to utilize its functionalities and UI components. The class declares various UI components like buttons, VBoxes, TableView components for announcements and assignments, and labels.

The initialize method sets up the initial state of the course interface. Based on the user type (i.e., STUDENT or PROFESSOR), it adjusts the visibility of certain buttons (e.g., button_addAnnounce and button_createAssignment). The method also updates the course title label, and reads assignments and announcements from the database into the respective tables. For students, it sets an event handler to detect double-clicks on assignments in the table to navigate to the respective assignment page.

The **readAssignmentsIntoTable** and **readAnnouncementsIntoTable** methods read the assignments and announcements from the Firestore database and populate the respective tables with them.

The **handleButton_addAnnouncement** method allows professors to add a new announcement to the course. It provides a text area for the professor to write the announcement and a confirm button to save it while the **writeNewAnnouncement** method is responsible for saving the new announcement to the Firestore database.

The **handleButton_createAssignment** method transitions the view to the assignment creation page. While the **writeNewAssignment** method is present, its implementation seems to be pending.


### Assignment Page

![assignment](https://github.com/alexander-harmaty/THINKR-Learning-Management-System/assets/92049896/d17e3b46-fa0a-4b84-bb63-e0ea59eaf2e5)

This page showcases the details of an assignment, where professors can create assignments and graed submissions, while students can upload files and details for their submissions.

This controller inherits from HomePageController, making it a specialized version tailored to handle assignment-specific actions. The window utilizes various UI elements like buttons, text fields, text areas, and boxes for efficient data entry.

The **buildAssignment** method is used to dynamically create the assignment view depending on which user type is viewing. This includes the title, due date, assignment details, and various action buttons. The screenshot displays a view meant for the professor when creating an assignment before any submissions.

The **createAssignment** method is responsible for creating a new assignment and adding it to the Firestore database. This includes aetting assignment metadata and creating a submission entry for every student in the current course. (Nongraded asignments are marked with a temporary grade of -1) This method uses **ZonedDateTime** to handle the conversion of the assignment's due date to a **Timestamp** which is suitable for Firestore storage.


### Grades Page

#### Professor View

![profgrades](https://github.com/alexander-harmaty/THINKR-Learning-Management-System/assets/92049896/fcad476a-888d-4193-a928-2ed7fbb39c6f)

#### Student View

![grades](https://github.com/alexander-harmaty/THINKR-Learning-Management-System/assets/92049896/4ced1cd9-1978-4a70-b6d9-ab455b7c434e)

This page provides functionalities to display grades-related information. It offers a view of individual submissions and their grades, and it can also compute and display the average grade for a course. The controller integrates with the Firestore database to fetch submission data.
Users can view their grades for each assignment. 

This controller inherits from HomePageController, making it a specialized version tailored to handle assignment-specific actions. The page utilizes various UI components, particularly tables and columns that are meant to display grades-related information to the user.

The initialize method sets up the initial state of the grades interface. Depending on the user type (i.e., STUDENT or PROFESSOR), it configures the UI differently. For example, a professor or student will not see tables outside of their classes. This method sets up the table columns to display submissions and their related information, and t reads the submissions from the database and populates the table.
For professors, it also sets an event handler to detect double-clicks on submissions in the table for more detailed view.

The **readSubmissionIntoTable** method reads the submissions from the Firestore database and populates the table with them. Depending on the user's type (STUDENT or PROFESSOR), the data is filtered differently.

The **gradesListBuilder** method constructs a list of courses and their respective average grades while the **classAvgCalculator** calculates the average grade for a list of grades.

Additionally, there's functionality to calculate the GPA. 
Each grade entry also allows navigation back to the respective assignment page for detailed viewing.


### Calendar Page

![calendar](https://github.com/alexander-harmaty/THINKR-Learning-Management-System/assets/92049896/caa8f943-f2bc-4e85-8870-1c08d5ae5378)

This page provides functionalities to display a calendar view for both students and professors. It manages the behavior and events associated with the calendar interface of the system, integrating with the CalendarFX library to provide a user-friendly calendar tool. For students, it showcases the due dates for assignments across their courses, while for professors, it displays the due dates for assignments in the courses they teach. The controller seamlessly integrates with the Firestore database to fetch relevant data and populate the calendar.

The controller inherits from HomePageController to utilize its functionalities and UI components. The class declares various UI elements like buttons, VBoxes, and the primary CalendarView component associated with CalendarFX.

The initialize method sets up the initial state of the calendar interface, and reads entries from the database into the calendar.

The **readEntriesIntoCalendar** method reads the assignments from the Firestore database based on the user type and populates the calendar with them. This method is specialized for both "STUDENT" and "PROFESSOR" user types, with each type having its distinct calendar views.
For a student, the calendar will display assignments for all courses they are enrolled in.
For a professor, the calendar will display assignments for all the courses they teach.

The **entryFactory** method serves as a helper function to create a new calendar entry based on an assignment. This method converts the Firestore Timestamp of the assignment's due date into a LocalDateTime that can be used with CalendarFX.

### Admin Page

![admin](https://github.com/alexander-harmaty/THINKR-Learning-Management-System/assets/92049896/40cf24fd-173e-4c91-941d-3a45052f937e)

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

The **CurrentUser** class extends the **User** class and holds the details of the logged-in user. 
The App.java class contains a static variable **currentUser** of type **CurrentUser**.

When a user decides to log out, the **logOut()** method is called. This method sets all the user information fields to their default or empty values. The method also navigates the user back to the login screen by calling **App.setRoot("LoginRegister");**.

In the **HomePageController** class, the **button_logout** button's action is set to call the **logOut()** method from the **CurrentUser** class.



## Status

The THINKR Learning Management System was conceived and executed as a pivotal capstone project for the Software Engineering class at Farmingdale State College. The project spanned the duration of the Fall 2022 semester and reached its culmination with a final presentation in December. Embodying the principles of the Software Development Life Cycle (SDLC), the project was systematically ushered through stages of planning, design, implementation, testing, and deployment.

We are proud to note that the project was not only well-received by our professor but also earned full marks, standing out as the top-scoring project in the class. This recognition underscores the team's dedication, hard work, and adherence to software engineering best practices.

Following the successful presentation and acknowledgment of its merits, the project transitioned to its final stage in the SDLC: closure. As such, the THINKR Learning Management System was officially marked as "completed," and no further development is anticipated for the foreseeable future.


## Credits
