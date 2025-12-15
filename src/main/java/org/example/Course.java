package org.example;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private List<Assignment> assignments;
    private List<Student> registeredStudents;
    private List<Double> finalScores;
    private static int nextId = 1;

    public Course(String courseName, double credits, Department department) {
        this.courseId = String.format("C-%s-%02d", department.getDepartmentId(), nextId++);
        this.courseName = courseName;
        this.credits = credits;
        this.department = department;
        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
        this.finalScores = new ArrayList<>();
    }

    /**
     * Checks if the sum of all assignment weights equals 100%.
     * @return true if weights sum to 100%, false otherwise
     */
    public boolean isAssignmentWeightValid() {
        double totalWeight = 0;
        for (Assignment assignment : assignments) {
            totalWeight += assignment.getWeight();
        }
        return Math.abs(totalWeight - 100.0) < 0.01; // Allow for floating point precision
    }

    /**
     *  Registers a student for the course
     *  Adds the student to the course's list and adds null score placeholders for all assignments.
     *  Returns false if the student is already registered.
     * @param student the Student to register
     * @return true if registration successful, false if already registered
     */
    public boolean registerStudent(Student student) {
        if (registeredStudents.contains(student)) {
            return false;
        }

        registeredStudents.add(student);
        finalScores.add(null);

        // Add null score for each assignment
        for (Assignment assignment : assignments) {
            assignment.addNullScore();
        }

        return true;
    }

    /**
     * Calculates the weighted average grade for each registered student.
     * @return array of rounded average scores for each student
     */
    public int[] calcStudentsAverage() {
        int[] averages = new int[registeredStudents.size()];

        for (int i = 0; i < registeredStudents.size(); i++) {
            double weightedAvg = 0;
            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(i);
                if (score != null) {
                    weightedAvg += score * (assignment.getWeight() / 100.0);
                }
            }
            averages[i] = (int) Math.round(weightedAvg);
        }

        return averages;
    }

    /**
     * Adds a new assignment to the course.
     * @param assignmentName the name of the assignment
     * @param weight the weight percentage of this assignment
     * @return true (always succeeds)
     */
    public boolean addAssignment(String assignmentName, double weight) {
        Assignment assignment = new Assignment(assignmentName, weight);
        // Add null scores for existing students
        for (int i = 0; i < registeredStudents.size(); i++) {
            assignment.addNullScore();
        }
        assignments.add(assignment);
        return true;
    }

    /**
     * Generates random scores for all assignments and calculates final grades
     */
    public void generateScores() {
        // Generate scores for each assignment
        for (Assignment assignment : assignments) {
            assignment.generateRandomScore();
        }

        // Calculate final scores
        finalScores.clear();
        int[] averages = calcStudentsAverage();
        for (int avg : averages) {
            finalScores.add((double) avg);
        }
    }

    /**
     * Displays all course grades in a formatted table
     */
    public void displayScores() {
        System.out.println("\nCourse: " + courseName + " (" + courseId + ")");
        System.out.println("Assignment Weight Valid: " + isAssignmentWeightValid() + "\n");

        // Calculate column widths
        int nameColumnWidth = 20;
        int scoreColumnWidth = 12;

        // Print header
        System.out.printf("%-" + nameColumnWidth + "s", "Student");
        for (Assignment assignment : assignments) {
            System.out.printf("%" + scoreColumnWidth + "s", assignment.getAssignmentName());
        }
        System.out.printf("%" + scoreColumnWidth + "s\n", "Final Score");

        // Print student scores
        int[] studentAverages = calcStudentsAverage();
        for (int i = 0; i < registeredStudents.size(); i++) {
            Student student = registeredStudents.get(i);
            System.out.printf("%-" + nameColumnWidth + "s", student.getStudentName());

            for (Assignment assignment : assignments) {
                Integer score = assignment.getScores().get(i);
                System.out.printf("%" + scoreColumnWidth + "s", score != null ? score : "-");
            }

            System.out.printf("%" + scoreColumnWidth + "d\n", studentAverages[i]);
        }

        // Print assignment averages
        System.out.printf("%-" + nameColumnWidth + "s", "Average");
        for (Assignment assignment : assignments) {
            double avg = assignment.calcAssignmentAvg();
            System.out.printf("%" + scoreColumnWidth + ".0f", avg);
        }
        System.out.println();
    }

    public String toSimplifiedString() {
        return courseId + " - " + courseName + " (" + credits + " credits) - " + department.getDepartmentId();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Course ID: ").append(courseId).append("\n");
        sb.append("Course Name: ").append(courseName).append("\n");
        sb.append("Credits: ").append(credits).append("\n");
        sb.append("Department: ").append(department).append("\n");
        sb.append("Assignment Weight Valid: ").append(isAssignmentWeightValid()).append("\n");
        sb.append("Assignments:\n");

        if (assignments.isEmpty()) {
            sb.append("  None");
        } else {
            for (Assignment assignment : assignments) {
                sb.append("  ").append(assignment).append("\n");
            }
        }

        sb.append("Registered Students:\n");
        if (registeredStudents.isEmpty()) {
            sb.append("  None");
        } else {
            for (Student student : registeredStudents) {
                sb.append("  ").append(student.toSimplifiedString()).append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Course course = (Course) obj;
        return Double.compare(course.credits, credits) == 0 &&
                courseId.equals(course.courseId) &&
                courseName.equals(course.courseName);
    }
}
