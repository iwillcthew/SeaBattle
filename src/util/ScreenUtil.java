package util;

import java.util.Scanner;

public class ScreenUtil {
    public static void waitForEnter() {
        System.out.println("\u001B[33mPress Enter to continue...\u001B[0m");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void clearScreen() {
        for (int i = 0; i < 30; i++) {
            System.out.println();
        }
    }
}