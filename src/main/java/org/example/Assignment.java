package org.example;

import lombok.Getter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private List<Integer> scores;
    private static int nextId = 1;

    public Assignment(String assignmentName, double weight) {
        this.assignmentId = String.format("A%03d", nextId++);
        this.assignmentName = assignmentName;
        this.weight = weight;
        this.scores = new ArrayList<>();
    }

    public void addScore(Integer score) {
        scores.add(score);
    }

    public void addNullScore() {
        scores.add(null);
    }

    public List<Integer> getScores() {
        return scores;
    }

    public void setScores(List<Integer> scores) {
        this.scores = scores;
    }

    /**
     * Calculates the average score for this assignment across all students.
     * @return the average score, or 0 if no scores exist
     */
    public double calcAssignmentAvg() {
        if (scores == null || scores.isEmpty()) {
            return 0;
        }

        int sum = 0;
        int count = 0;
        for (Integer score : scores) {
            if (score != null) {
                sum += score;
                count++;
            }
        }

        return count == 0 ? 0 : (double) sum / count;
    }

    /**
     * Generates random scores for all students with null scores.
     */
    public void generateRandomScore() {
        List<Integer> newScores = new ArrayList<>();
        for (Integer score : scores) {
            if (score == null) {
                newScores.add(generateSingleRandomScore());
            } else {
                newScores.add(score);
            }
        }
        this.scores = newScores;
    }

    /**
     * Returns a string representation of the assignment.
     * @return formatted assignment string
     */
    private int generateSingleRandomScore() {
        Random random = new Random();
        int randNum = random.nextInt(11); // 0 to 10

        if (randNum == 0) {
            return random.nextInt(60); // [0, 60)
        } else if (randNum == 1 || randNum == 2) {
            return 60 + random.nextInt(10); // [60, 70)
        } else if (randNum == 3 || randNum == 4) {
            return 70 + random.nextInt(10); // [70, 80)
        } else if (randNum == 5 || randNum == 6 || randNum == 7 || randNum == 8) {
            return 80 + random.nextInt(10); // [80, 90)
        } else { // randNum == 9 || randNum == 10
            return 90 + random.nextInt(11); // [90, 100]
        }
    }

    @Override
    public String toString() {
        return assignmentId + " - " + assignmentName + " (Weight: " + weight + "%)";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Assignment assignment = (Assignment) obj;
        return Double.compare(assignment.weight, weight) == 0 &&
                assignmentId.equals(assignment.assignmentId) &&
                assignmentName.equals(assignment.assignmentName);
    }
}
