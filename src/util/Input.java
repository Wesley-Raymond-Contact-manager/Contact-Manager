package util;

import java.util.Scanner;

public class Input {
    private final Scanner scanner;

    public Input() {
        scanner = new Scanner(System.in);
    }

    public String getString() {
        return scanner.nextLine();
    }

    public String getString(String prompt) {
        System.out.println(prompt);
        return scanner.nextLine();
    }

    public String getNextString() {
        return scanner.next();
    }

    public String getNextString(String prompt) {
        System.out.println(prompt);
        return scanner.next();
    }

    public boolean yesNo() {
        String userInput = scanner.nextLine();
        return userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes");
    }

    public boolean yesNo(String prompt) {
        System.out.println(prompt);
        String userInput = scanner.nextLine();
        return userInput.equalsIgnoreCase("y") || userInput.equalsIgnoreCase("yes");
    }

    public int getInt() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return getInt();
        }
    }

    public int getInt(String prompt) {
        try {
            System.out.println(prompt);
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return getInt(prompt);
        }
    }

    public int getInt(int min, int max, String prompt) {
        try {
            System.out.println(prompt);
            int userInput = Integer.parseInt(scanner.nextLine());
            if (userInput < min || userInput > max) {
                System.out.printf("%d is not a valid number between %d and %d%n", userInput, min, max);
                return getInt(min, max, prompt);
            }
            return userInput;
        } catch (NumberFormatException e) {
            return getInt(min, max, prompt);
        }
    }

    public double getDouble(String prompt) {
        try {
            System.out.println(prompt);
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return getDouble(prompt);
        }
    }

    public double getDouble() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return getDouble();
        }
    }

    public double getDouble(double min, double max, String prompt) {
        try {
            System.out.println(prompt);
            double userInput = Double.parseDouble(scanner.nextLine());
            if (userInput < min || userInput > max) {
                System.out.printf("%f is not a valid number between %f and %f%n", userInput, min, max);
                return getDouble(min, max, prompt);
            }
            return userInput;
        } catch (NumberFormatException e) {
            return getDouble(min, max, prompt);
        }
    }

    public int getBinary() {
        return Integer.parseInt(scanner.nextLine(), 2);
    }

    public int getBinary(String prompt) {
        System.out.println(prompt);
        return Integer.parseInt(scanner.nextLine(), 2);
    }

    public long getHex() {
        return Long.parseLong(scanner.nextLine(), 16);
    }

    public long getHex(String prompt) {
        System.out.println(prompt);
        return Long.parseLong(scanner.nextLine(), 16);
    }

    public void close() {
        scanner.close();
    }

}

