package com.healthtracker.cli;

import com.healthtracker.model.MoodEntry;
import com.healthtracker.service.MoodTracker;

import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

public class MenuSystem {
    private final MoodTracker tracker;
    private final Scanner scanner;

    public MenuSystem() {
        this.tracker = new MoodTracker();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getMenuChoice();
            running = handleChoice(choice);
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("\n=== Mental Health Tracker ===");
        System.out.println("1. Record Today's Mood");
        System.out.println("2. View Mood History");
        System.out.println("3. View Average Mood");
        System.out.println("4. View Common Symptoms");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
    }

    private int getMenuChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private boolean handleChoice(int choice) {
        switch (choice) {
            case 1:
                recordMood();
                break;
            case 2:
                viewMoodHistory();
                break;
            case 3:
                viewAverageMood();
                break;
            case 4:
                viewCommonSymptoms();
                break;
            case 5:
                System.out.println("Thanks for using my Mental Health Tracker!");
                return false;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return true;
    }
    private void recordMood() {
        System.out.print("Enter mood rating (1-5, where 1 is lowest and 5 is highest): ");
        try {
            int rating = Integer.parseInt(scanner.nextLine());
            if (rating < 1 || rating > 5) {
                System.out.println("Invalid rating. Must be between 1 and 5.");
                return;
            }

            MoodEntry entry = new MoodEntry(LocalDate.now(), rating);

            System.out.println("Enter symptoms (or press Enter to skip):");
            String symptom;
            while (true) {
                System.out.print("Symptom (or press Enter to continue): ");
                symptom = scanner.nextLine().trim();
                if (symptom.isEmpty()) {
                    break;
                }
                entry.addSymptom(symptom);
            }

            System.out.print("Would you like to add any notes about how you're currently feeling? (or press Enter to skip): ");
            String notes = scanner.nextLine().trim();
            if (!notes.isEmpty()) {
                entry.setNotes(notes);
            }

            tracker.addMoodEntry(entry);
            System.out.println("Mood recorded successfully!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
    }
    private void viewMoodHistory() {
        System.out.println("\n=== Mood History ===");
        if (tracker.getMoodHistory().isEmpty()) {
            System.out.println("No mood entries recorded yet.");
            return;
        }
        tracker.getMoodHistory().forEach(entry ->
                System.out.printf("Date: %s, Mood: %d, Symptoms: %s%s%n",
                        entry.getDate(),
                        entry.getMoodRating(),
                        String.join(", ", entry.getSymptoms()),
                        entry.getNotes() != null && !entry.getNotes().isEmpty()
                                ? ", Notes: " + entry.getNotes()
                                : "")
        );
    }

    private void viewAverageMood() {
        double average = tracker.getAverageMoodRating();
        if (average == 0.0) {
            System.out.println("No mood entries recorded yet.");
            return;
        }
        System.out.printf("Average Mood Rating: %.2f%n", average);
    }

    private void viewCommonSymptoms() {
        System.out.println("\n=== Common Symptoms ===");
        Map<String, Integer> symptoms = tracker.getCommonSymptoms();
        if (symptoms.isEmpty()) {
            System.out.println("No symptoms recorded yet.");
            return;
        }
        symptoms.forEach((symptom, count) ->
                System.out.printf("%s: reported %d time(s)%n", symptom, count)
        );
    }

    public static void main(String[] args) {
        new MenuSystem().start();
    }
}