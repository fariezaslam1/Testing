package com.example.model;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

public class QuestTest {

    @Test
    public void testQuestConstructor_ValidInputs() {
        LocalDateTime deadline = LocalDateTime.now().plusDays(7);
        Quest quest = new Quest("Test Quest", "A quest description", 5, "Gold Coins", deadline);

        assertEquals("Test Quest", quest.getTitle());
        assertEquals("A quest description", quest.getDescription());
        assertEquals(5, quest.getDifficulty());
        assertEquals("Gold Coins", quest.getReward());
        assertEquals(deadline, quest.getDeadline());
    }

    @Test
    public void testQuestConstructor_InvalidInputs() {
        LocalDateTime deadline = LocalDateTime.now().minusDays(1); // Past deadline

        assertThrows(IllegalArgumentException.class, () -> {
            new Quest("", "A quest description", 5, "Gold Coins", LocalDateTime.now().plusDays(7));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Quest("Test Quest", "", 5, "Gold Coins", LocalDateTime.now().plusDays(7));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Quest("Test Quest", "A quest description", 0, "Gold Coins", LocalDateTime.now().plusDays(7));
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Quest("Test Quest", "A quest description", 5, "Gold Coins", deadline);
        });
    }

    @Test
    public void testSettersAndUpdate() {
        LocalDateTime originalDeadline = LocalDateTime.now().plusDays(7);
        Quest quest = new Quest("Test Quest", "A quest description", 5, "Gold Coins", originalDeadline);

        // Update fields
        LocalDateTime newDeadline = LocalDateTime.now().plusDays(10);
        quest.updateDetails("Updated Quest", "Updated description", 7, "Diamond Coins", newDeadline);

        assertEquals("Updated Quest", quest.getTitle());
        assertEquals("Updated description", quest.getDescription());
        assertEquals(7, quest.getDifficulty());
        assertEquals("Diamond Coins", quest.getReward());
        assertEquals(newDeadline, quest.getDeadline());
    }

    @Test
    public void testInvalidUpdates() {
        Quest quest = new Quest("Test Quest", "A quest description", 5, "Gold Coins", LocalDateTime.now().plusDays(7));

        assertThrows(IllegalArgumentException.class, () -> quest.setTitle(""));
        assertThrows(IllegalArgumentException.class, () -> quest.setDescription(""));
        assertThrows(IllegalArgumentException.class, () -> quest.setDifficulty(0));
        assertThrows(IllegalArgumentException.class, () -> quest.setDeadline(LocalDateTime.now().minusDays(1)));
    }

    @Test
    public void testToString() {
        LocalDateTime deadline = LocalDateTime.now().plusDays(7);
        Quest quest = new Quest("Test Quest", "A quest description", 5, "Gold Coins", deadline);

        String expected = String.format(
                "Quest: Test Quest | Difficulty: 5 | Reward: Gold Coins | Deadline: %s | Description: A quest description",
                deadline.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        assertEquals(expected, quest.toString());
    }
}
