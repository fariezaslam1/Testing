package com.example.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.example.model.Quest;

public class QuestStorageTest {
    @Test
    public void testAddQuest() {
        QuestStorage questStorage = new QuestStorage();
        Quest quest = new Quest("New Quest", "Description of new quest", 7);

        questStorage.addQuest(quest);
        assertEquals(1, questStorage.getAllQuests().split("\n").length); // One quest should be added
    }

    @Test
    void testDeleteQuest() {

    }

    @Test
    void testGetAllQuests() {

    }

    @Test
    void testGetQuestById() {

    }

    @Test
    void testUpdateQuest() {

    }
}
