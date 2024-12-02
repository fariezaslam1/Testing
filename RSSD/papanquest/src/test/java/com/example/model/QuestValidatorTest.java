package com.example.model;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

public class QuestValidatorTest {

    // Jalur 1: Valid Quest
    @Test
    public void testValidQuest() {
        QuestValidator validator = new QuestValidator();
        Quest validQuest = new Quest("Valid Quest", "A valid quest description", 5, "Gold Reward",
                LocalDateTime.now().plusDays(7));

        // Valid quest should pass validation
        assertTrue(validator.validate(validQuest), "Valid quest should pass validation");
    }

    // Jalur 2: Invalid Quest - Title Kosong
    @Test
    public void testQuestWithoutTitle() {
        QuestValidator validator = new QuestValidator();
        Quest invalidQuest = new Quest(null, "A valid quest description", 5, "Gold Reward",
                LocalDateTime.now().plusDays(7));

        // Quest without title should fail validation
        assertFalse(validator.validate(invalidQuest), "Quest without title should fail validation");
    }

    // Jalur 3: Invalid Quest - Description Kosong
    @Test
    public void testQuestWithoutDescription() {
        QuestValidator validator = new QuestValidator();
        Quest invalidQuest = new Quest("Valid Quest", null, 5, "Gold Reward", LocalDateTime.now().plusDays(7));

        // Quest without description should fail validation
        assertFalse(validator.validate(invalidQuest), "Quest without description should fail validation");
    }

    // Jalur 4: Invalid Quest - Difficulty Tidak Valid (Kurang dari 1)
    @Test
    public void testQuestWithInvalidDifficultyLow() {
        QuestValidator validator = new QuestValidator();
        Quest invalidQuest = new Quest("Valid Quest", "A valid quest description", 0, "Gold Reward",
                LocalDateTime.now().plusDays(7));

        // Quest with difficulty less than 1 should fail validation
        assertFalse(validator.validate(invalidQuest), "Quest with difficulty less than 1 should fail validation");
    }

    // Jalur 4: Invalid Quest - Difficulty Tidak Valid (Lebih dari 10)
    @Test
    public void testQuestWithInvalidDifficultyHigh() {
        QuestValidator validator = new QuestValidator();
        Quest invalidQuest = new Quest("Valid Quest", "A valid quest description", 11, "Gold Reward",
                LocalDateTime.now().plusDays(7));

        // Quest with difficulty greater than 10 should fail validation
        assertFalse(validator.validate(invalidQuest), "Quest with difficulty greater than 10 should fail validation");
    }
}
