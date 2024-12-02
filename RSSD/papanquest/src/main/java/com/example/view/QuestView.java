package com.example.view;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import com.example.model.Quest;

public class QuestView {

    private final Scanner scanner = new Scanner(System.in);
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    // Mengambil input judul quest
    public String getInputTitle() {
        System.out.print("Enter quest title: ");
        return scanner.nextLine();
    }

    // Mengambil input deskripsi quest
    public String getInputDescription() {
        System.out.print("Enter quest description: ");
        return scanner.nextLine();
    }

    // Mengambil input tingkat kesulitan quest
    public int getInputDifficulty() {
        System.out.print("Enter difficulty (1-10): ");
        int difficulty = scanner.nextInt();
        scanner.nextLine(); // Konsumsi new line
        return difficulty;
    }

    // Mengambil input reward quest
    public String getInputReward() {
        System.out.print("Enter upah: ");
        return scanner.nextLine();
    }

    // Mengambil input Quest ID untuk penghapusan atau operasi lainnya
    public int getInputQuestId() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the Quest ID to take: ");
        return scanner.nextInt();
    }

    // Mengambil input batas waktu quest dalam bentuk tanggal dan waktu
    public LocalDateTime getInputDeadline() {
        while (true) {
            try {
                System.out.print("Enter deadline (yyyy-MM-dd HH:mm): ");
                String input = scanner.nextLine();
                return LocalDateTime.parse(input, dateTimeFormatter);
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format! Please enter the date and time in the format yyyy-MM-dd HH:mm.");
            }
        }
    }

    // Menampilkan pesan sukses
    public void displaySuccessMessage() {
        System.out.println("Operation successful!");
    }

    public void displayQuestInfo(Quest quest) {
        System.out.println("Quest Information:");
        System.out.println("Title: " + quest.getTitle());
        System.out.println("Description: " + quest.getDescription());
        System.out.println("Difficulty: " + quest.getDifficulty());
        System.out.println("Reward: " + quest.getReward());
        System.out.println("Deadline: " + quest.getDeadline());
    }

    // Menampilkan pesan error
    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    // Menambahkan metode untuk mengupdate quest
    public void displayUpdateQuestForm() {
        System.out.print("Enter quest ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Ambil data baru dari pengguna
        String title = getInputTitle();
        String description = getInputDescription();
        int difficulty = getInputDifficulty();
        String reward = getInputReward();
        LocalDateTime deadline = getInputDeadline();

        // Mengembalikan data yang diinput
    }

}
