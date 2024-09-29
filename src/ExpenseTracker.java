import java.time.*;
import java.util.*;

public class ExpenseTracker {
    private final List<Expense> expenses;
    private int nextID;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
        nextID = 1;
    }

    public void addExpense(String description, double amount) {
        Expense expense = new Expense(nextID++, LocalDate.now(), description, amount);
        expenses.add(expense);
        System.out.println("Expenses added successfully (ID: " + expense.getID() + ")");
    }

    public void updateExpense(int id, String description, double amount) {
        for (Expense expense : expenses) {
            if (expense.getID() == id) {
                expense.setDescription(description);
                expense.setAmount(amount);
                System.out.println("Expenses updated successfully");
                return;
            }
        }
        System.out.println("Expenses not found!");
    }

    public void listExpenses() {
        System.out.printf("%-3s  %-11s  %-11s  %10s%n", "ID", "Date", "Description", "Amount");
        for (Expense expense : expenses) {
            System.out.println(expense);
        }
    }

    public void summary() {
        double total = expenses.stream().mapToDouble(Expense::getAmount).sum();
        System.out.println("Total expenses: ₱" + total);
    }

    public void summaryByMonth(int month) {
        double total = expenses.stream()
                .filter(e -> e.getDate().getMonth() == Month.of(month))
                .mapToDouble(Expense::getAmount)
                .sum();
        System.out.println("Total expenses for " + Month.of(month) + ": ₱" + total);
    }

    public void deleteExpense(int id) {
        expenses.removeIf(expense -> expense.getID() == id);
        System.out.println("Expense deleted successfully");
    }
}


