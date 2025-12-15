package org.example;

public class Main {
    static void main() {
        // Create departments
        Department csDept = new Department("Computer Science");
        Department mathDept = new Department("Mathematics");

        // Create addresses
        Address addr1 = new Address(123, "Main Street", "Toronto", Province.ONTARIO, "M1A2B3");
        Address addr2 = new Address(456, "Oak Avenue", "Vancouver", Province.BRITISH_COLUMBIA, "V6B4X8");
        Address addr3 = new Address(789, "Elm Street", "Montreal", Province.QUEBEC, "H2X1Y7");

        // Create students
        Student student1 = new Student("John Smith", Gender.MALE, addr1, csDept);
        Student student2 = new Student("Jane Doe", Gender.FEMALE, addr2, csDept);
        Student student3 = new Student("Bob Johnson", Gender.MALE, addr3, mathDept);

        // Create courses
        Course course1 = new Course("Programming 1", 3.0, csDept);
        Course course2 = new Course("Data Structures", 3.5, csDept);

        // Add assignments to course1
        course1.addAssignment("Assignment 1", 20);
        course1.addAssignment("Assignment 2", 20);
        course1.addAssignment("Assignment 3", 20);
        course1.addAssignment("Exam 1", 20);
        course1.addAssignment("Exam 2", 20);

        // Register students to course1
        course1.registerStudent(student1);
        course1.registerStudent(student2);
        course1.registerStudent(student3);

        // Add assignments to course2
        course2.addAssignment("Quiz 1", 15);
        course2.addAssignment("Quiz 2", 15);
        course2.addAssignment("Project", 30);
        course2.addAssignment("Final Exam", 40);

        // Register students to course2
        course2.registerStudent(student1);
        course2.registerStudent(student2);

        // Register courses with students
        student1.registerCourse(course1);
        student1.registerCourse(course2);
        student2.registerCourse(course1);
        student2.registerCourse(course2);
        student3.registerCourse(course1);

        System.out.println("========== STUDENTS ==========\n");
        System.out.println(student1.toString());
        System.out.println(student2.toString());
        System.out.println(student3.toString());

        System.out.println("\n========== COURSES BEFORE SCORES ==========\n");
        System.out.println(course1.toString());
        System.out.println("\n" + course2.toString());

        // Generate scores
        System.out.println("\n========== GENERATING SCORES ==========\n");
        course1.generateScores();
        course2.generateScores();

        // Display scores in table format
        System.out.println("========== COURSE SCORES ==========");
        course1.displayScores();
        course2.displayScores();

        // Test student drop course
        System.out.println("\n========== STUDENT DROPS COURSE ==========");
        student1.dropCourse(course2);
        System.out.println(student1.toSimplifiedString() + " dropped " + course2.getCourseName());

        // Display course1 after drop
        System.out.println("\n========== UPDATED COURSE SCORES ==========");
        course1.displayScores();
    }
}
