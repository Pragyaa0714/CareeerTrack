# CareerTrack – Job Application Tracker

CareerTrack is a **Java-based Job Application Tracker** that helps users manage and monitor their job applications from the command line.

The application allows users to add job applications, view and search applications, update application status, delete applications, and view application statistics.

## 🚀 Features

* ➕ Add a new job application
* 📋 View all job applications
* 🔍 Search applications by company or job role
* 🔄 Update application status
* 🗑️ Delete applications
* 📊 View application statistics
* 💾 Automatically save application data
* 🔁 Load previously saved applications when the program starts

## 📌 Application Status

Each application can have one of the following statuses:

* Applied
* Screening
* Interview
* Offer
* Rejected

## 🛠️ Technologies Used

* **Java**
* Java Collections (`ArrayList`)
* Java Serialization
* File Handling
* Object-Oriented Programming
* Command-Line Interface (CLI)

## 📂 Data Storage

CareerTrack stores application data locally using Java object serialization.

The application uses:

```text
applications.dat
```

The data is automatically saved when applications are added, updated, deleted, or when the program exits.

## ▶️ How to Run

### 1. Clone the repository

```bash
git clone https://github.com/Pragyaa0714/careertrack.git
```

### 2. Open the project

Open the project folder in **VS Code**, IntelliJ IDEA, Eclipse, or another Java IDE.

### 3. Compile the program

```bash
javac Main.java
```

### 4. Run the program

```bash
java Main
```

## 📋 Main Menu

After running the program, the following options are available:

```text
1. Add Application
2. View Applications
3. Search Applications
4. Update Status
5. Delete Application
6. View Statistics
7. Exit
```

## 📊 Statistics

CareerTrack provides statistics for:

* Total applications
* Applied applications
* Screening applications
* Interviews
* Offers
* Rejected applications
* Interview rate

## 🎯 Purpose

This project was created to practice and demonstrate practical Java programming concepts such as:

* Classes and Objects
* Encapsulation
* Enums
* ArrayList
* Loops and Conditional Statements
* File Handling
* Serialization
* Searching and Updating Data
* Basic Statistics

## 🔮 Future Improvements

Possible future improvements include:

* GUI-based interface
* Database integration using SQLite/MySQL
* Login and user accounts
* Application deadlines and reminders
* Sorting and filtering
* Export applications to CSV/PDF
* Dashboard with charts

## 👩‍💻 Author

**Pragya Kumari**

B.Tech – Artificial Intelligence & Data Science

---

⭐ If you find this project useful, feel free to star the repository!
