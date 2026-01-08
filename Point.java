package com.example.demo.model;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "points")
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "volunteer_id")
    private Long volunteerId;

    @Column(name = "points_earned")
    private int pointsEarned;

    @Column(name = "earned_date")
    private LocalDate earnedDate;

    // Constructors, Getters, and Setters

    public Point() {
    }

    public Point(Long volunteerId, int pointsEarned, LocalDate earnedDate) {
        this.volunteerId = volunteerId;
        this.pointsEarned = pointsEarned;
        this.earnedDate = earnedDate;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(Long volunteerId) {
        this.volunteerId = volunteerId;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public LocalDate getEarnedDate() {
        return earnedDate;
    }

    public void setEarnedDate(LocalDate earnedDate) {
        this.earnedDate = earnedDate;
    }
}
