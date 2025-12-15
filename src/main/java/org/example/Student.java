package org.example;

import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private List<Course> registeredCourses;
    private static int nextId = 1;

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("S%06d", nextId++);
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.registeredCourses = new ArrayList<>();
    }

    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            return false;
        }

        registeredCourses.add(course);

        course.getRegisteredStudents().add(this);
        for (Assignment assignment : course.getAssignments()) {
            assignment.addNullScore();
        }

        return true;
    }

    /**
     *  Drops a course
     *  Removes the course from the student's list and removes the student from the course's list
     *  Also removes the student's scores from all assignments in the course
     *  Returns false if the student is not registered for the course
     * @param course the Course to drop
     * @return true if drop successful, false if not registered
     */
    public boolean dropCourse(Course course) {
        // Check if course is registered
        if (!registeredCourses.contains(course)) {
            return false;
        }

        // Remove course from student's list
        registeredCourses.remove(course);

        // Remove student from course's list and remove their scores
        course.getRegisteredStudents().remove(this);
        int studentIndex = course.getRegisteredStudents().size();
        for (Assignment assignment : course.getAssignments()) {
            if (studentIndex < assignment.getScores().size()) {
                assignment.getScores().remove(studentIndex);
            }
        }

        return true;
    }

    public String toSimplifiedString() {
        return studentId + " - " + studentName + " (" + department.getDepartmentId() + ")";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student ID: ").append(studentId).append("\n");
        sb.append("Name: ").append(studentName).append("\n");
        sb.append("Gender: ").append(gender).append("\n");
        sb.append("Address: ").append(address).append("\n");
        sb.append("Department: ").append(department).append("\n");
        sb.append("Registered Courses:\n");

        if (registeredCourses.isEmpty()) {
            sb.append("  None");
        } else {
            for (Course course : registeredCourses) {
                sb.append("  ").append(course.getCourseName())
                        .append(" (").append(course.getCourseId()).append(") ")
                        .append("- ").append(course.getDepartment().getDepartmentId()).append("\n");
            }
        }

        return sb.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Student student = (Student) obj;
        return studentId.equals(student.studentId) &&
                studentName.equals(student.studentName) &&
                gender == student.gender;
    }
}
