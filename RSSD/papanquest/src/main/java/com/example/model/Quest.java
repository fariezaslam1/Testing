package com.example.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Quest {
    private String title; // Tidak lagi final agar bisa diupdate
    private String description;
    private int difficulty;
    private String reward;
    private LocalDateTime deadline; // Tidak lagi final agar bisa diupdate

    // Konstruktor yang menerima semua parameter termasuk deadline
    public Quest(String title, String description, int difficulty, String reward, LocalDateTime deadline) {
        this.title = title;
        this.description = description;
        this.difficulty = difficulty;
        this.reward = reward;
        this.deadline = deadline;
    }

    // Getter untuk setiap atribut
    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getReward() {
        return reward;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    // Setter untuk setiap atribut agar bisa diubah
    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    // Metode untuk memperbarui quest
    public void updateDetails(String newTitle, String newDescription, int newDifficulty, String newReward,
            LocalDateTime newDeadline) {
        setTitle(newTitle); // Memperbarui judul
        setDescription(newDescription); // Memperbarui deskripsi
        setDifficulty(newDifficulty); // Memperbarui tingkat kesulitan
        setReward(newReward); // Memperbarui reward
        setDeadline(newDeadline); // Memperbarui deadline
    }

    // Method untuk menampilkan informasi quest
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "Quest: " + title +
                " | Difficulty: " + difficulty +
                " | Reward: " + reward +
                " | Deadline: " + deadline.format(formatter) +
                " | Description: " + description;
    }
}
