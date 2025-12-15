import org.example.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {

    // ========== ADDRESS TESTS ==========
    @Test
    @DisplayName("Address: Valid postal code M1A2B3")
    public void testValidPostalCodeM1A2B3() {
        assertTrue(Address.isPostalCodeValid("M1A2B3"));
    }

    @Test
    @DisplayName("Address: Valid postal code V6B4X8")
    public void testValidPostalCodeV6B4X8() {
        assertTrue(Address.isPostalCodeValid("V6B4X8"));
    }

    @Test
    @DisplayName("Address: Valid postal code K1A0B1")
    public void testValidPostalCodeK1A0B1() {
        assertTrue(Address.isPostalCodeValid("K1A0B1"));
    }

    @Test
    @DisplayName("Address: Invalid postal code - too short")
    public void testInvalidPostalCodeTooShort() {
        assertFalse(Address.isPostalCodeValid("M1A2B"));
    }

    @Test
    @DisplayName("Address: Invalid postal code - too long")
    public void testInvalidPostalCodeTooLong() {
        assertFalse(Address.isPostalCodeValid("M1A2B34"));
    }

    @Test
    @DisplayName("Address: Invalid postal code - empty")
    public void testInvalidPostalCodeEmpty() {
        assertFalse(Address.isPostalCodeValid(""));
    }

    @Test
    @DisplayName("Address: Invalid postal code - null")
    public void testInvalidPostalCodeNull() {
        assertFalse(Address.isPostalCodeValid(null));
    }

    @Test
    @DisplayName("Address: Invalid - starts with digit")
    public void testInvalidPostalCodeStartsWithDigit() {
        assertFalse(Address.isPostalCodeValid("1A1A1A"));
    }

    @Test
    @DisplayName("Address: Invalid - all letters")
    public void testInvalidPostalCodeAllLetters() {
        assertFalse(Address.isPostalCodeValid("AAAAAA"));
    }

    @Test
    @DisplayName("Address: Invalid - all digits")
    public void testInvalidPostalCodeAllDigits() {
        assertFalse(Address.isPostalCodeValid("111111"));
    }

    @Test
    @DisplayName("Address: Constructor valid")
    public void testAddressConstructorValid() {
        Address addr = new Address(123, "Main Street", "Toronto", Province.ONTARIO, "M1A2B3");
        assertNotNull(addr.getPostalCode());
        assertEquals("M1A2B3", addr.getPostalCode());
        assertEquals("Main Street", addr.getStreet());
        assertEquals(123, addr.getStreetNo());
    }

    @Test
    @DisplayName("Address: Constructor invalid")
    public void testAddressConstructorInvalid() {
        Address addr = new Address(456, "Oak Avenue", "Vancouver", Province.BRITISH_COLUMBIA, "invalid");
        assertNull(addr.getPostalCode());
        assertNull(addr.getStreet());
    }

    @Test
    @DisplayName("Address: Uppercase conversion")
    public void testPostalCodeUppercase() {
        Address addr = new Address(789, "Elm Street", "Montreal", Province.QUEBEC, "h2x1y7");
        assertEquals("H2X1Y7", addr.getPostalCode());
    }

    @Test
    @DisplayName("Address: toString valid")
    public void testAddressToStringValid() {
        Address addr = new Address(123, "Main Street", "Toronto", Province.ONTARIO, "M1A2B3");
        String result = addr.toString();
        assertTrue(result.contains("Main Street"));
        assertTrue(result.contains("M1A2B3"));
    }

    @Test
    @DisplayName("Address: toString invalid")
    public void testAddressToStringInvalid() {
        Address addr = new Address(456, "Oak Avenue", "Vancouver", Province.BRITISH_COLUMBIA, "invalid");
        assertEquals("Address{invalid}", addr.toString());
    }

    @Test
    @DisplayName("Address: Equals")
    public void testAddressEquals() {
        Address addr1 = new Address(123, "Main St", "Toronto", Province.ONTARIO, "M1A2B3");
        Address addr2 = new Address(123, "Main St", "Toronto", Province.ONTARIO, "M1A2B3");
        assertEquals(addr1, addr2);
    }

    // ========== DEPARTMENT TESTS ==========
    @Test
    @DisplayName("Department: Valid - Computer Science")
    public void testValidDepartmentComputerScience() {
        assertTrue(Department.isDepartmentNameValid("Computer Science"));
    }

    @Test
    @DisplayName("Department: Valid - Mathematics")
    public void testValidDepartmentMathematics() {
        assertTrue(Department.isDepartmentNameValid("Mathematics"));
    }

    @Test
    @DisplayName("Department: Invalid - contains numbers")
    public void testInvalidDepartmentWithNumbers() {
        assertFalse(Department.isDepartmentNameValid("CS123"));
    }

    @Test
    @DisplayName("Department: Invalid - special characters")
    public void testInvalidDepartmentSpecialChars() {
        assertFalse(Department.isDepartmentNameValid("Science@"));
    }

    @Test
    @DisplayName("Department: Invalid - empty")
    public void testInvalidDepartmentEmpty() {
        assertFalse(Department.isDepartmentNameValid(""));
    }

    @Test
    @DisplayName("Department: Invalid - null")
    public void testInvalidDepartmentNull() {
        assertFalse(Department.isDepartmentNameValid(null));
    }

    @Test
    @DisplayName("Department: ID format check")
    public void testDepartmentIdFormat() {
        Department dept = new Department("Physics");
        assertNotNull(dept.getDepartmentId());
        assertTrue(dept.getDepartmentId().matches("D\\d{2}"));
    }

    @Test
    @DisplayName("Department: Invalid creation")
    public void testInvalidDepartmentCreation() {
        Department dept = new Department("CS123");
        assertNull(dept.getDepartmentId());
        assertNull(dept.getDepartmentName());
    }

    // ========== ASSIGNMENT TESTS ==========
    @Test
    @DisplayName("Assignment: Creation")
    public void testAssignmentCreation() {
        Assignment assignment = new Assignment("Quiz", 20);
        assertNotNull(assignment.getAssignmentId());
        assertEquals("Quiz", assignment.getAssignmentName());
        assertEquals(20, assignment.getWeight());
    }

    @Test
    @DisplayName("Assignment: Add score")
    public void testAssignmentAddScore() {
        Assignment assignment = new Assignment("Test", 30);
        assignment.addScore(85);
        assertEquals(1, assignment.getScores().size());
        assertEquals(85, assignment.getScores().get(0));
    }

    @Test
    @DisplayName("Assignment: Add null")
    public void testAssignmentAddNull() {
        Assignment assignment = new Assignment("Test", 30);
        assignment.addNullScore();
        assertEquals(1, assignment.getScores().size());
        assertNull(assignment.getScores().get(0));
    }

    @Test
    @DisplayName("Assignment: Average calculation")
    public void testAssignmentAverage() {
        Assignment assignment = new Assignment("Test", 30);
        assignment.addScore(80);
        assignment.addScore(90);
        assignment.addScore(85);
        assertEquals(85, assignment.calcAssignmentAvg());
    }

    @Test
    @DisplayName("Assignment: Average with nulls")
    public void testAssignmentAverageWithNulls() {
        Assignment assignment = new Assignment("Test", 30);
        assignment.addScore(80);
        assignment.addScore(null);
        assignment.addScore(90);
        assertEquals(85, assignment.calcAssignmentAvg());
    }

    @Test
    @DisplayName("Assignment: Average empty")
    public void testAssignmentAverageEmpty() {
        Assignment assignment = new Assignment("Test", 30);
        assertEquals(0, assignment.calcAssignmentAvg());
    }

    @Test
    @DisplayName("Assignment: Random score generation")
    public void testAssignmentRandomScore() {
        Assignment assignment = new Assignment("Test", 30);
        assignment.addNullScore();
        assignment.addNullScore();
        assignment.generateRandomScore();

        for (Integer score : assignment.getScores()) {
            assertNotNull(score);
            assertTrue(score >= 0 && score <= 100);
        }
    }

    // ========== STUDENT TESTS ==========
    @Test
    @DisplayName("Student: Creation")
    public void testStudentCreation() {
        Address addr = new Address(123, "Main Street", "Toronto", Province.ONTARIO, "M1A2B3");
        Department dept = new Department("Physics");
        Student student = new Student("John Smith", Gender.MALE, addr, dept);

        assertNotNull(student.getStudentId());
        assertEquals("John Smith", student.getStudentName());
        assertEquals(Gender.MALE, student.getGender());
        assertTrue(student.getStudentId().matches("S\\d{6}"));
    }

    @Test
    @DisplayName("Student: Register course")
    public void testStudentRegisterCourse() {
        Address addr = new Address(123, "Main Street", "Toronto", Province.ONTARIO, "M1A2B3");
        Department dept = new Department("Chemistry");
        Student student = new Student("Alice", Gender.FEMALE, addr, dept);
        Course course = new Course("Intro", 3.0, dept);

        course.addAssignment("A1", 50);
        course.addAssignment("E1", 50);

        boolean result = student.registerCourse(course);
        assertTrue(result);
        assertTrue(student.getRegisteredCourses().contains(course));
    }

    @Test
    @DisplayName("Student: Duplicate registration")
    public void testStudentDuplicateRegister() {
        Address addr = new Address(123, "Main Street", "Toronto", Province.ONTARIO, "M1A2B3");
        Department dept = new Department("Biology");
        Student student = new Student("Bob", Gender.MALE, addr, dept);
        Course course = new Course("101", 3.0, dept);

        course.addAssignment("A1", 50);
        course.addAssignment("E1", 50);

        student.registerCourse(course);
        boolean result = student.registerCourse(course);
        assertFalse(result);
    }

    @Test
    @DisplayName("Student: Drop course")
    public void testStudentDropCourse() {
        Address addr = new Address(123, "Main Street", "Toronto", Province.ONTARIO, "M1A2B3");
        Department dept = new Department("History");
        Student student = new Student("Carol", Gender.FEMALE, addr, dept);
        Course course = new Course("101", 3.0, dept);

        course.addAssignment("A1", 50);
        course.addAssignment("E1", 50);

        student.registerCourse(course);
        boolean result = student.dropCourse(course);
        assertTrue(result);
        assertFalse(student.getRegisteredCourses().contains(course));
    }

    @Test
    @DisplayName("Student: Drop non-registered")
    public void testStudentDropNonRegistered() {
        Address addr = new Address(123, "Main Street", "Toronto", Province.ONTARIO, "M1A2B3");
        Department dept = new Department("English");
        Student student = new Student("David", Gender.MALE, addr, dept);
        Course course = new Course("101", 3.0, dept);

        boolean result = student.dropCourse(course);
        assertFalse(result);
    }

    // ========== COURSE TESTS ==========
    @Test
    @DisplayName("Course: Creation")
    public void testCourseCreation() {
        Department dept = new Department("Art");
        Course course = new Course("Drawing", 3.0, dept);

        assertNotNull(course.getCourseId());
        assertEquals("Drawing", course.getCourseName());
        assertEquals(3.0, course.getCredits());
        assertTrue(course.getCourseId().contains("C-"));
    }

    @Test
    @DisplayName("Course: Add assignment")
    public void testCourseAddAssignment() {
        Department dept = new Department("Music");
        Course course = new Course("Piano", 3.0, dept);

        boolean result = course.addAssignment("Recital", 50);
        assertTrue(result);
        assertEquals(1, course.getAssignments().size());
    }

    @Test
    @DisplayName("Course: Weight validation - valid")
    public void testCourseWeightValid() {
        Department dept = new Department("Dance");
        Course course = new Course("Ballet", 3.0, dept);

        course.addAssignment("A1", 30);
        course.addAssignment("A2", 40);
        course.addAssignment("E1", 30);

        assertTrue(course.isAssignmentWeightValid());
    }

    @Test
    @DisplayName("Course: Weight validation - invalid")
    public void testCourseWeightInvalid() {
        Department dept = new Department("Theater");
        Course course = new Course("Drama", 3.0, dept);

        course.addAssignment("A1", 30);
        course.addAssignment("A2", 40);

        assertFalse(course.isAssignmentWeightValid());
    }

    @Test
    @DisplayName("Course: Register student")
    public void testCourseRegisterStudent() {
        Department dept = new Department("Sports");
        Course course = new Course("Soccer", 3.0, dept);
        Address addr = new Address(123, "Main Street", "Toronto", Province.ONTARIO, "M1A2B3");
        Student student = new Student("Eve", Gender.FEMALE, addr, dept);

        course.addAssignment("Game", 50);
        course.addAssignment("Training", 50);

        boolean result = course.registerStudent(student);
        assertTrue(result);
        assertTrue(course.getRegisteredStudents().contains(student));
    }

    @Test
    @DisplayName("Course: Calculate average")
    public void testCourseCalculateAverage() {
        Department dept = new Department("Economics");
        Course course = new Course("Micro", 3.0, dept);
        Address addr = new Address(123, "Main Street", "Toronto", Province.ONTARIO, "M1A2B3");
        Student s1 = new Student("Frank", Gender.MALE, addr, dept);
        Student s2 = new Student("Grace", Gender.FEMALE, addr, dept);

        course.addAssignment("Assignment", 40);
        course.addAssignment("Exam", 60);

        course.registerStudent(s1);
        course.registerStudent(s2);

        course.getAssignments().get(0).getScores().set(0, 80);
        course.getAssignments().get(0).getScores().set(1, 90);
        course.getAssignments().get(1).getScores().set(0, 75);
        course.getAssignments().get(1).getScores().set(1, 85);

        int[] averages = course.calcStudentsAverage();
        assertEquals(2, averages.length);
        assertEquals(77, averages[0]);
        assertEquals(87, averages[1]);
    }

    @Test
    @DisplayName("Course: Generate scores")
    public void testCourseGenerateScores() {
        Department dept = new Department("Statistics");
        Course course = new Course("Probability", 3.0, dept);
        Address addr = new Address(123, "Main Street", "Toronto", Province.ONTARIO, "M1A2B3");
        Student student = new Student("Henry", Gender.MALE, addr, dept);

        course.addAssignment("HW", 30);
        course.addAssignment("Quiz", 30);
        course.addAssignment("Final", 40);

        course.registerStudent(student);
        course.generateScores();

        for (Assignment assignment : course.getAssignments()) {
            for (Integer score : assignment.getScores()) {
                assertTrue(score >= 0 && score <= 100);
            }
        }
    }

    // ========== UTIL TESTS ==========
    @Test
    @DisplayName("Util: toTitleCase simple")
    public void testToTitleCaseSimple() {
        assertEquals("John Smith", Util.toTitleCase("john smith"));
    }

    @Test
    @DisplayName("Util: toTitleCase multiple words")
    public void testToTitleCaseMultiple() {
        assertEquals("Computer Science", Util.toTitleCase("computer science"));
    }

    @Test
    @DisplayName("Util: toTitleCase single")
    public void testToTitleCaseSingle() {
        assertEquals("Word", Util.toTitleCase("word"));
    }

    @Test
    @DisplayName("Util: toTitleCase empty")
    public void testToTitleCaseEmpty() {
        assertEquals("", Util.toTitleCase(""));
    }

    @Test
    @DisplayName("Util: toTitleCase null")
    public void testToTitleCaseNull() {
        assertNull(Util.toTitleCase(null));
    }

    // ========== INTEGRATION TESTS ==========
    @Test
    @DisplayName("Integration: Complete workflow")
    public void testCompleteWorkflow() {
        Department dept = new Department("Literature");
        Address addr = new Address(123, "Main Street", "Toronto", Province.ONTARIO, "M1A2B3");
        Student student = new Student("Iris", Gender.FEMALE, addr, dept);
        Course course = new Course("Novels", 3.0, dept);

        course.addAssignment("Report", 30);
        course.addAssignment("Essay", 30);
        course.addAssignment("Exam", 40);

        course.registerStudent(student);
        student.registerCourse(course);

        assertTrue(student.getRegisteredCourses().contains(course));
        assertTrue(course.isAssignmentWeightValid());

        course.generateScores();
        int[] avg = course.calcStudentsAverage();
        assertEquals(1, student.getRegisteredCourses().size());
    }

    @Test
    @DisplayName("Integration: Multiple students")
    public void testMultipleStudents() {
        Department dept = new Department("Chemistry");
        Address addr = new Address(123, "Main Street", "Toronto", Province.ONTARIO, "M1A2B3");

        Student s1 = new Student("Jack", Gender.MALE, addr, dept);
        Student s2 = new Student("Kate", Gender.FEMALE, addr, dept);
        Student s3 = new Student("Leo", Gender.MALE, addr, dept);

        Course course = new Course("Organic", 3.0, dept);
        course.addAssignment("Lab", 50);
        course.addAssignment("Exam", 50);

        course.registerStudent(s1);
        course.registerStudent(s2);
        course.registerStudent(s3);

        assertEquals(3, course.getRegisteredStudents().size());
    }
}