package com.healthtracker.service.service;

import com.healthtracker.model.MoodEntry;
import com.healthtracker.service.MoodTracker;;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Map;

public class MoodTrackerTest {
    private MoodTracker tracker;

    @BeforeEach
    void setUp() {
        tracker = new MoodTracker();
    }

    @Test
    void testAddMoodEntry() {
        LocalDate today = LocalDate.now();
        MoodEntry entry = new MoodEntry(today, 4);
        tracker.addMoodEntry(entry);

        assertEquals(entry, tracker.getMoodEntry(today));
    }

    @Test
    void testAddNullEntry() {
        assertThrows(IllegalArgumentException.class, () -> tracker.addMoodEntry(null));
    }

    @Test
    void testGetAverageMoodRating() {
        LocalDate today = LocalDate.now();
        tracker.addMoodEntry(new MoodEntry(today, 4));
        tracker.addMoodEntry(new MoodEntry(today.minusDays(1), 2));

        assertEquals(3.0, tracker.getAverageMoodRating());
    }

    @Test
    void testGetCommonSymptoms() {
        LocalDate today = LocalDate.now();
        MoodEntry entry1 = new MoodEntry(today, 3);
        entry1.addSymptom("Anxiety");
        entry1.addSymptom("Insomnia");

        MoodEntry entry2 = new MoodEntry(today.minusDays(1), 4);
        entry2.addSymptom("Anxiety");

        tracker.addMoodEntry(entry1);
        tracker.addMoodEntry(entry2);

        Map<String, Integer> symptoms = tracker.getCommonSymptoms();
        assertEquals(2, symptoms.get("Anxiety"));
        assertEquals(1, symptoms.get("Insomnia"));
    }

    @Test
    void testEmptyTrackerAverageRating() {
        assertEquals(0.0, tracker.getAverageMoodRating());
    }
}