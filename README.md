Final Project
by Tergah Bafumba-Morneault


Final project for Vanier College 101 programming

A Java-based course management system that handles student registration, course management, assignment tracking and grade calculation.

It has features such as: Student Management,Course Management,Assignment Management,Department Management,Address Management,Grade Calculation

Project Structure
src/
├── main/java/org/example/
│   ├── Gender.java              # Gender enumeration (MALE, FEMALE)
│   ├── Province.java            # Canadian provinces with abbreviations
│   ├── Address.java             # Address with postal code validation
│   ├── Department.java          # Department management
│   ├── Assignment.java          # Assignment with score tracking
│   ├── Student.java             # Student with course registration
│   ├── Course.java              # Course with grade management
│   ├── Util.java                # Utility functions
│   └── Main.java                # Demo application
└── test/java/
    └── CourseManagementTest.java # 50+ unit test

Technology used
Java 8+
Maven
JUnit 5 
lombok
Intellij IDEA

How to use:
1. clone repository
2. Build the project
3. Run the application
4. Run the tests

File                                Description
Gender.java                         Gender enumeration (MALE, FEMALE)
Province.java                       Canadian provinces with abbreviations
Address.java                        Address management with postal code validation
Department.java                     Department management with ID generation
Assignment.java                     Assignment management with score tracking
Student.java                        Student management with course enrollment
Course.java                         Course management with grade calculation
Util.java                           Utility functions (title case conversion)
Main.java                           Demo application
MainTest.java                       50+ unit tests
    
