CodeSnippetOrganizer

A Java-based application designed to store, manage, and organize reusable code snippets efficiently.

About the Project

CodeSnippetOrganizer is a Java application that provides a structured way to manage code snippets. It demonstrates core Object-Oriented Programming concepts, modular package organization, custom exception handling, and file handling.

The application uses a text file for persistent storage of code snippets.

Features

- Organize code snippets using a structured application design.
- Store and retrieve snippets using file handling.
- Handle invalid inputs through custom exceptions.
- Manage empty snippet lists with exception handling.
- Handle storage-related errors.
- Maintain modular code using separate Java packages.

Technologies Used

- Programming Language: Java
- JDK Version: Java 21
- IDE: Eclipse
- Concepts: Object-Oriented Programming, Exception Handling, File Handling

Project Structure

CodeSnippetOrganizer/
│
├── src/
│   ├── App/
│   │   └── MainApp.java
│   │
│   ├── Exception/
│   │   ├── EmptySnippetListException.java
│   │   ├── InvalidInputException.java
│   │   ├── SnippetNotFoundException.java
│   │   └── StorageFullException.java
│   │
│   ├── Model/
│   │   └── Snippet.java
│   │
│   ├── Service/
│   │   └── snippetService.java
│   │
│   └── module-info.java
│
└── snippets.txt


Requirements

- Java Development Kit (JDK) 21
- Eclipse IDE or another Java IDE

How to Run

1. Clone or download this repository.
2. Open Eclipse IDE.
3. Import the project using **File → Import → Existing Projects into Workspace**.
4. Ensure that JDK 21 is configured.
5. Locate `MainApp.java` inside the `App` package.
6. Run the application.

Concepts Demonstrated

- Classes and Objects
- Encapsulation
- Modular Package Organization
- Custom Exception Handling
- File Handling and Data Persistence
- Java Collections (if implemented)

Author

Nithilan V K

Computer Science and Engineering Student

---
⭐ If you find this project useful, feel free to explore the code and provide suggestions!
