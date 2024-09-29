import java.time.LocalDate;

public class Expense {
    private final int ID;
    private final LocalDate date;
    private String description;
    private double amount;

    public Expense(int ID, LocalDate date, String description, double amount) {
        this.ID = ID;
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public int getID() {
        return ID;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return String.format("%-4d | %-10s | %-15s | ₱%10.2f", ID, date, description, amount);
    }
}
