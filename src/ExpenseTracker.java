import java.time.*;
import java.util.*;
import com.google.gson.*;
import java.nio.file.*;

public class ExpenseTracker {
    private final List<Expense> expenses;
    private int nextID;
    private final Gson gson;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
        nextID = 1;
        gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, LocalDateAdapter.SERIALIZER)
                .registerTypeAdapter(LocalDate.class, LocalDateAdapter.DESERIALIZER)
                .create();
        loadExpenses();
    }

    public void addExpense(String description, double amount) {
        Expense expense = new Expense(nextID++, LocalDate.now(), description, amount);
        expenses.add(expense);
        saveExpenses();
        System.out.println("Expense added successfully (ID: " + expense.getID() + ")");
    }

    public void updateExpense(int id, String description, double amount) {
        for (Expense expense : expenses) {
            if (expense.getID() == id) {
                expense.setDescription(description);
                expense.setAmount(amount);
                saveExpenses();
                System.out.println("Expense updated successfully");
                return;
            }
        }
        System.out.println("Expense not found!");
    }

    public void deleteExpense(int id) {
        expenses.removeIf(expense -> expense.getID() == id);
        saveExpenses();
        System.out.println("Expense deleted successfully");
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

    private void saveExpenses() {
        try {
            String json = gson.toJson(expenses);
            Files.write(Paths.get("expenses.json"), json.getBytes());
        } catch (Exception e) {
            System.out.println("Failed to save expenses");
            e.printStackTrace();
        }
    }

    private void loadExpenses() {
        try {
            String json = Files.readString(Paths.get("expenses.json"));
            Expense[] array = gson.fromJson(json, Expense[].class);
            expenses.addAll(Arrays.asList(array));
            nextID = expenses.stream().mapToInt(Expense::getID).max().orElse(0) + 1;
        } catch (Exception e) {
            System.out.println("Failed to load expenses");
        }
    }
}