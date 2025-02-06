package com.healthtracker.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MoodEntry {
    private LocalDate date;
    private int moodRating;
    private List<String> symptoms;
    private String notes;

    public MoodEntry(LocalDate date, int moodRating) {
        validateMoodRating(moodRating);
        this.date = date;
        this.moodRating = moodRating;
        this.symptoms = new ArrayList<>();
    }

    private void validateMoodRating(int rating) {
        if (rating < 1 || rating > 5) {
            throw new IllegalArgumentException("Mood rating must be between 1 and 5");
        }
    }    public LocalDate getDate() {
        return date;
    }

    public int getMoodRating() {
        return moodRating;
    }

    public void setMoodRating(int moodRating) {
        validateMoodRating(moodRating);
        this.moodRating = moodRating;
    }

    public List<String> getSymptoms() {
        return new ArrayList<>(symptoms);
    }

    public void addSymptom(String symptom) {
        if (symptom != null && !symptom.trim().isEmpty()) {
            symptoms.add(symptom);
        }
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
