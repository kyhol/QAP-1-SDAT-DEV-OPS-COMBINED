package com.healthtracker.service.model;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import com.healthtracker.model.MoodEntry;

public class MoodEntryTest {
    @Test
    void testInvalidMoodRatingTooHigh() {
        assertThrows(IllegalArgumentException.class, () -> new MoodEntry(LocalDate.now(), 6));
    }

    @Test
    void testInvalidMoodRatingTooLow() {
        assertThrows(IllegalArgumentException.class, () -> new MoodEntry(LocalDate.now(), 0));
    }

    @Test
    void testValidMoodRating() {
        MoodEntry entry = new MoodEntry(LocalDate.now(), 3);
        assertEquals(3, entry.getMoodRating());
    }
    @Test
    void testAddValidSymptom() {
        MoodEntry entry = new MoodEntry(LocalDate.now(), 3);
        entry.addSymptom("Anxiety");
        assertTrue(entry.getSymptoms().contains("Anxiety"));
    }

    @Test
    void testAddEmptySymptom() {
        MoodEntry entry = new MoodEntry(LocalDate.now(), 3);
        entry.addSymptom("");
        assertTrue(entry.getSymptoms().isEmpty());
    }
}