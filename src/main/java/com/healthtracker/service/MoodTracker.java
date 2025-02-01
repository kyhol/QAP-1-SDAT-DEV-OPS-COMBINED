package com.healthtracker.service;

import com.healthtracker.model.MoodEntry;
import java.time.LocalDate;
import java.util.*;

public class MoodTracker {
    private Map<LocalDate, MoodEntry> moodEntries;

    public MoodTracker() {
        this.moodEntries = new HashMap<>();
    }

    public void addMoodEntry(MoodEntry entry) {
        if (entry == null) {
            throw new IllegalArgumentException("Entry cannot be null");
        }
        moodEntries.put(entry.getDate(), entry);
    }

    public MoodEntry getMoodEntry(LocalDate date) {
        return moodEntries.get(date);
    }

    public double getAverageMoodRating() {
        if (moodEntries.isEmpty()) {
            return 0.0;
        }
        return moodEntries.values().stream()
                .mapToInt(MoodEntry::getMoodRating)
                .average()
                .orElse(0.0);
    }

    public List<MoodEntry> getMoodHistory() {
        return new ArrayList<>(moodEntries.values());
    }

    public Map<String, Integer> getCommonSymptoms() {
        Map<String, Integer> symptomFrequency = new HashMap<>();

        moodEntries.values().forEach(entry -> {
            entry.getSymptoms().forEach(symptom ->
                    symptomFrequency.merge(symptom, 1, Integer::sum)
            );
        });

        return symptomFrequency;
    }
}