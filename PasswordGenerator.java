import java.util.Scanner;
import java.util.Random;

public class PasswordGenerator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Generating the password
        System.out.println("Do you want to use uppercase letters? (Yes/No)");
        boolean useUppercase = scanner.nextLine().equalsIgnoreCase("Yes");

        System.out.println("Do you want to use lowercase letters? (Yes/No)");
        boolean useLowercase = scanner.nextLine().equalsIgnoreCase("Yes");

        System.out.println("Do you want to use numbers? (Yes/No)");
        boolean useNumbers = scanner.nextLine().equalsIgnoreCase("Yes");

        System.out.println("Do you want to use symbols? (Yes/No)");
        boolean useSymbols = scanner.nextLine().equalsIgnoreCase("Yes");

        System.out.println("Enter the desired length of the password:");
        int length = scanner.nextInt();

        String password = generatePassword(useUppercase, useLowercase, useNumbers, useSymbols, length);
        System.out.println("Generated Password: " + password);

        // Checking the password's strength
        String strength = checkPasswordStrength(password);
        System.out.println("Password Strength: " + strength);

        // Displaying useful information
        displayPasswordSecurityInfo();

        scanner.close();
    }

    private static String generatePassword(boolean useUppercase, boolean useLowercase, boolean useNumbers, boolean useSymbols, int length) {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String symbols = "!@#$%^&*()-_+=<>?";

        String passwordAlphabet = "";
        if (useUppercase) {
            passwordAlphabet += upperCaseLetters;
        }
        if (useLowercase) {
            passwordAlphabet += lowerCaseLetters;
        }
        if (useNumbers) {
            passwordAlphabet += numbers;
        }
        if (useSymbols) {
            passwordAlphabet += symbols;
        }

        if (passwordAlphabet.isEmpty()) {
            throw new IllegalArgumentException("At least one character type must be selected!");
        }

        Random random = new Random();
        StringBuilder password = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(passwordAlphabet.length());
            password.append(passwordAlphabet.charAt(index));
        }
        return password.toString();
    }

    private static String checkPasswordStrength(String password) {
        int score = 0;
        if (password.length() >= 8) {
            score++;
        }
        if (password.length() >= 16) {
            score++;
        }
        if (password.matches(".*[A-Z].*")) {
            score++;
        }
        if (password.matches(".*[a-z].*")) {
            score++;
        }
        if (password.matches(".*\\d.*")) {
            score++;
        }
        if (password.matches(".*[!@#$%^&*()-_+=<>?].*")) {
            score++;
        }

        switch (score) {
            case 0:
            case 1:
            case 2:
                return "Weak";
            case 3:
            case 4:
                return "Medium";
            case 5:
                return "Good";
            case 6:
                return "Great";
            default:
                return "Unknown";
        }
    }

    private static void displayPasswordSecurityInfo() {
        System.out.println("Password Security Tips:");
        System.out.println("1. Avoid using the same password twice.");
        System.out.println("2. Avoid character repetition.");
        System.out.println("3. Avoid keyboard patterns.");
        System.out.println("4. Avoid dictionary words.");
        System.out.println("5. Avoid letter or number sequences.");
        System.out.println("6. Use a mix of uppercase, lowercase, numbers, and symbols.");
        System.out.println("7. Make your password at least 16 characters long for better security.");
    }
}
