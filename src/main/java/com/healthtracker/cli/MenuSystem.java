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
    private void recordMood() {}
    private void viewMoodHistory() {

    }
    private void viewAverageMood() {}
    private void viewCommonSymptoms() {}
    private void viewMood() {}
    public static void main(String[] args) {}

}