# IT Help Desk Ticket System

## Overview
A Java-based IT Help Desk Ticket Management System designed to create, manage, and track technical support requests.

I developed this project to practice object-oriented programming, data structures, file handling, and IT support workflows.

## Features
- Create support tickets with unique IDs
- View all existing tickets
- Search for tickets by ID using binary search
- Update ticket status and priority
- Delete tickets with confirmation
- Automatically sort tickets by ID
- Save and load tickets using CSV files
- Handle invalid user inputs

## Technologies Used
- Java
- ArrayList
- Binary Search
- CSV File Handling
- Object-Oriented Programming
- Exception Handling

## Project Structure
- `Main.java` - Handles the user interface and menu
- `Ticket.java` - Represents individual support tickets
- `TicketManager.java` - Manages ticket operations
- `CSVManager.java` - Saves and loads ticket information
- `tickets.csv` - Stores ticket records

## How to Run
1. Download or clone the repository.
2. Open the project folder in VS Code or another Java IDE.
3. Compile the Java files.
4. Run `Main.java`.
5. Use the menu to manage support tickets.

## Ticket Information
Each ticket contains:
- Ticket ID
- Subject
- Description
- Priority (LOW, MEDIUM, HIGH)
- Status (OPEN, IN_PROGRESS, CLOSED)
- Creation date and time

## What I Learned
- Applying object-oriented programming to a practical application
- Managing collections of Java objects
- Implementing binary search for ticket lookup
- Reading and writing CSV files
- Handling exceptions and validating user input
- Persisting application data between sessions

## Future Improvements
- Add technician assignments
- Implement user accounts and authentication
- Create a graphical user interface
- Add ticket categories and filtering
- Integrate a SQL database
- Add ticket activity history

## Purpose
This project was developed to strengthen my Java programming skills and gain experience building software related to IT support and ticket management.