package com.example.storage;

import java.util.ArrayList;
import java.util.List;

import com.example.model.Quest;

public class QuestStorage {

    private final List<Quest> quests = new ArrayList<>();

    public void addQuest(Quest quest) {
        quests.add(quest);
        System.out.println("Quest saved successfully!");
    }

    public void updateQuest(int idQuest, Quest updatedQuest) {
        // Update quest berdasarkan ID
        if (idQuest > 0 && idQuest <= quests.size()) {
            quests.set(idQuest - 1, updatedQuest); // Menimpa quest yang lama dengan quest yang baru
            System.out.println("Quest updated successfully!");
        } else {
            System.out.println("Quest not found.");
        }
    }

    public void deleteQuest(int idQuest) {
        if (idQuest <= 0 || idQuest > quests.size()) {
            System.out.println("Quest not found.");
        } else {
            quests.remove(idQuest - 1);
            System.out.println("Quest deleted successfully!");
        }
    }

    public String getAllQuests() {
        if (quests.isEmpty()) {
            return "No quests available.\n";
        }

        StringBuilder result = new StringBuilder("All Submitted Quests:\n");
        int id = 1;
        for (Quest quest : quests) {
            result.append("Quest ID: ").append(id).append(" | ").append(quest).append("\n");
            id++;
        }
        return result.toString();
    }

    public Quest getQuestById(int idQuest) {
        if (idQuest > 0 && idQuest <= quests.size()) {
            return quests.get(idQuest - 1); // ID dimulai dari 1, jadi kita perlu mengurangi 1
        }
        return null;
    }
}
