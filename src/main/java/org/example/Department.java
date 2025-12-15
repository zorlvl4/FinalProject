package org.example;

import lombok.Getter;

@Getter
public class Department {
    private String departmentId;
    private String departmentName;
    private static int nextId = 1;

    public Department(String departmentName) {
        if (isDepartmentNameValid(departmentName)) {
            this.departmentId = String.format("D%02d", nextId++);
            this.departmentName = departmentName;
        } else {
            this.departmentId = null;
            this.departmentName = null;
        }
    }

    /**
     * Validates if a department name is valid.
     * @param departmentName the name to validate
     * @return true if valid, false otherwise
     */
    public static boolean isDepartmentNameValid(String departmentName) {
        if (departmentName == null || departmentName.isEmpty()) {
            return false;
        }

        for (char c : departmentName.toCharArray()) {
            if (!Character.isLetter(c) && c != ' ') {
                return false;
            }
        }
        return true;
    }

    /**
     * Sets the department name with validation.
     * @param departmentName the new department name
     */
    public void setDepartmentName(String departmentName) {
        if (isDepartmentNameValid(departmentName)) {
            this.departmentName = departmentName;
        }
    }

    @Override
    public String toString() {
        if (departmentId == null || departmentName == null) {
            return "Department{invalid}";
        }
        return departmentId + " - " + departmentName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Department dept = (Department) obj;
        return departmentId.equals(dept.departmentId) &&
                departmentName.equals(dept.departmentName);
    }
}

