package ui;
import model.Transaction;
import model.TransactionType;
import model.Category;
import service.TransactionService;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
private final TransactionService service = new TransactionService();
private final Scanner scanner = new Scanner(System.in);
public void start(){
    boolean running = true;
    while (running){
        printMenu();
        int choice = getInt("Choose an option: ");
        switch (choice) {
            case 1 -> addTransaction();
            case 2 -> viewTransactions();
            case 3 -> showReport();
            case 4 -> running = false;
            default -> System.out.println("Invalid choice.");
        }
        }
    System.out.println("Goodbye!");
    }
    private void printMenu() {
        System.out.println("\n==== Personal Finance Tracker ====");
        System.out.println("1. Add Transaction");
        System.out.println("2. View Transactions");
        System.out.println("3. Show Report");
        System.out.println("4. Exit");
    }

    private void addTransaction() {
        System.out.println("\n--- Add Transaction ---");

        TransactionType type = getTransactionType();
        Category category = getCategory();
        double amount = getDouble("Amount: ");
        scanner.nextLine(); // consume newline
        System.out.print("Description: ");
        String description = scanner.nextLine();
        LocalDate date = LocalDate.now(); // Could allow custom input

        Transaction t = new Transaction(type, category, amount, date, description);
        service.addTransaction(t);
        System.out.println("Transaction added.");
    }

    private void viewTransactions() {
        System.out.println("\n--- All Transactions ---");
        List<Transaction> list = service.getAllTransactions();
        if (list.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        list.forEach(System.out::println);
    }

    private void showReport() {
        System.out.println("\n--- Report ---");
        System.out.printf("Total Income:  $%.2f\n", service.getTotalIncome());
        System.out.printf("Total Expense: $%.2f\n", service.getTotalExpenses());
        System.out.printf("Balance:       $%.2f\n", service.getBalance());
    }

    // --- Helpers ---

    private TransactionType getTransactionType() {
        while (true) {
            System.out.print("Type (1 = Income, 2 = Expense): ");
            int choice = scanner.nextInt();
            if (choice == 1) return TransactionType.INCOME;
            if (choice == 2) return TransactionType.EXPENSE;
            System.out.println("Invalid input.");
        }
    }

    private Category getCategory() {
        System.out.println("Categories:");
        for (int i = 0; i < Category.values().length; i++) {
            System.out.printf("%d. %s\n", i + 1, Category.values()[i]);
        }
        while (true) {
            int index = getInt("Choose category: ");
            if (index >= 1 && index <= Category.values().length) {
                return Category.values()[index - 1];
            }
            System.out.println("Invalid choice.");
        }
    }

    private int getInt(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Enter a number: ");
        }
        return scanner.nextInt();
    }

    private double getDouble(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextDouble()) {
            scanner.next();
            System.out.print("Enter a valid number: ");
        }
        return scanner.nextDouble();
    }
}