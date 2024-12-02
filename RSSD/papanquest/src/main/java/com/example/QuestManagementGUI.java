package com.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.controller.QuestController;
import com.example.model.QuestValidator;
import com.example.storage.QuestStorage;
import com.example.storage.ReviewStorage;
import com.example.view.QuestView;
import com.example.view.ReviewView;

public class QuestManagementGUI {
    private JFrame frame;
    private JTextArea outputArea;
    private QuestController controller;

    public QuestManagementGUI() {
        // Inisialisasi komponen
        QuestView view = new QuestView();
        ReviewView reviewView = new ReviewView();
        QuestValidator validator = new QuestValidator();
        QuestStorage questStorage = new QuestStorage();
        ReviewStorage reviewStorage = new ReviewStorage();
        controller = new QuestController(view, reviewView, validator, questStorage, reviewStorage); // Konstruktor
                                                                                                    // dengan parameter
                                                                                                    // yang benar

        initialize();
    }

    private void initialize() {
        frame = new JFrame("Quest Management System");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 1, 10, 10));

        // Tombol untuk setiap fitur
        JButton btnSubmitQuest = new JButton("Submit a new quest");
        JButton btnShowQuests = new JButton("Show all quests");
        JButton btnSubmitReview = new JButton("Submit a review");
        JButton btnShowReviews = new JButton("Show all reviews");
        JButton btnDeleteQuest = new JButton("Delete a quest");
        JButton btnExit = new JButton("Exit");

        // Tombol untuk Update Quest
        JButton btnUpdateQuest = new JButton("Update a quest");
        btnUpdateQuest.addActionListener(e -> showUpdateQuestForm()); // Memanggil fungsi untuk update quest
        panel.add(btnUpdateQuest);

        JButton btnTakeQuest = new JButton("Take a quest");
        btnTakeQuest.addActionListener(e -> controller.takeQuest());
        panel.add(btnTakeQuest);

        // Menambahkan tombol ke panel
        panel.add(btnSubmitQuest);
        panel.add(btnShowQuests);
        panel.add(btnSubmitReview);
        panel.add(btnShowReviews);
        panel.add(btnDeleteQuest);
        panel.add(btnExit);

        // Area output untuk log
        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Tambahkan panel tombol di sisi kiri dan area output di tengah
        frame.add(panel, BorderLayout.WEST);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Tambahkan ActionListener untuk setiap tombol
        btnSubmitQuest.addActionListener(e -> showSubmitQuestForm()); // Memanggil form Submit Quest
        btnShowQuests.addActionListener(e -> appendOutput("Showing all quests:\n" + controller.showAllQuests()));
        btnSubmitReview.addActionListener(e -> showSubmitReviewForm()); // Memanggil form Submit Review
        btnShowReviews.addActionListener(e -> appendOutput("Showing all reviews:\n" + controller.showAllReviews()));
        btnDeleteQuest.addActionListener(e -> showDeleteQuestForm()); // Memanggil form Delete Quest
        btnExit.addActionListener(e -> System.exit(0)); // Menutup aplikasi

        frame.setVisible(true);
    }

    private void appendOutput(String text) {
        outputArea.append(text + "\n");
        outputArea.setCaretPosition(outputArea.getDocument().getLength());
    }

    // Form untuk Delete Quest
    private void showDeleteQuestForm() {
        JTextField questIdField = new JTextField(20);
        JPanel formPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        formPanel.add(new JLabel("Quest ID:"));
        formPanel.add(questIdField);

        int result = JOptionPane.showConfirmDialog(frame, formPanel,
                "Delete a Quest", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int questId = Integer.parseInt(questIdField.getText());
                controller.deleteQuestFromGUI(questId);
                appendOutput("Quest with ID " + questId + " deleted successfully.\n");
            } catch (Exception ex) {
                appendOutput("Error: " + ex.getMessage());
            }
        }
    }

    // Form untuk Submit Review
    private void showSubmitReviewForm() {
        JTextField questIdField = new JTextField(20);
        JSpinner ratingSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 5, 1));
        JTextArea komentarArea = new JTextArea(5, 20);

        JPanel formPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        formPanel.add(new JLabel("Quest ID:"));
        formPanel.add(questIdField);
        formPanel.add(new JLabel("Rating (1-5):"));
        formPanel.add(ratingSpinner);
        formPanel.add(new JLabel("Comment:"));
        formPanel.add(new JScrollPane(komentarArea));

        int result = JOptionPane.showConfirmDialog(frame, formPanel,
                "Submit a Review", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int questId = Integer.parseInt(questIdField.getText());
                int rating = (int) ratingSpinner.getValue();
                String komentar = komentarArea.getText();
                controller.submitReviewFromGUI(questId, rating, komentar);
                appendOutput("New review submitted successfully.\n");
            } catch (Exception ex) {
                appendOutput("Error: " + ex.getMessage());
            }
        }
    }

    // Form untuk Submit Quest
    private void showSubmitQuestForm() {
        JTextField titleField = new JTextField(20);
        JTextArea descriptionArea = new JTextArea(5, 20);
        JSpinner difficultySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        JTextField rewardField = new JTextField(20);
        JTextField deadlineField = new JTextField(20);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(new JScrollPane(descriptionArea));
        formPanel.add(new JLabel("Difficulty (1-10):"));
        formPanel.add(difficultySpinner);
        formPanel.add(new JLabel("Reward:"));
        formPanel.add(rewardField);
        formPanel.add(new JLabel("Deadline (yyyy-MM-dd HH:mm):"));
        formPanel.add(deadlineField);

        int result = JOptionPane.showConfirmDialog(frame, formPanel,
                "Submit a New Quest", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                String title = titleField.getText();
                String description = descriptionArea.getText();
                int difficulty = (int) difficultySpinner.getValue();
                String reward = rewardField.getText();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime deadline = LocalDateTime.parse(deadlineField.getText(), formatter);

                controller.submitQuestFromGUI(title, description, difficulty, reward, deadline);
                appendOutput("New quest submitted successfully.");
            } catch (Exception ex) {
                appendOutput("Error: " + ex.getMessage());
            }
        }
    }

    // Form untuk Update Quest
    private void showUpdateQuestForm() {
        JTextField idField = new JTextField(20);
        JTextField titleField = new JTextField(20);
        JTextArea descriptionArea = new JTextArea(5, 20);
        JSpinner difficultySpinner = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
        JTextField rewardField = new JTextField(20);
        JTextField deadlineField = new JTextField(20);

        JPanel formPanel = new JPanel(new GridLayout(6, 2, 5, 5));
        formPanel.add(new JLabel("Quest ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Title:"));
        formPanel.add(titleField);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(new JScrollPane(descriptionArea));
        formPanel.add(new JLabel("Difficulty (1-10):"));
        formPanel.add(difficultySpinner);
        formPanel.add(new JLabel("Reward:"));
        formPanel.add(rewardField);
        formPanel.add(new JLabel("Deadline (yyyy-MM-dd HH:mm):"));
        formPanel.add(deadlineField);

        int result = JOptionPane.showConfirmDialog(frame, formPanel, "Update a Quest", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try {
                int idQuest = Integer.parseInt(idField.getText());
                String title = titleField.getText();
                String description = descriptionArea.getText();
                int difficulty = (int) difficultySpinner.getValue();
                String reward = rewardField.getText();
                LocalDateTime deadline = LocalDateTime.parse(deadlineField.getText(),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

                // Panggil metode untuk update quest
                controller.updateQuestFromGUI(idQuest, title, description, difficulty, reward, deadline);
            } catch (Exception ex) {
                appendOutput("Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                QuestManagementGUI window = new QuestManagementGUI();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
