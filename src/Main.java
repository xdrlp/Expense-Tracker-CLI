import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);
        printHelp();

        while (true) {
            try {
                System.out.print("₱ ");
                String command = scanner.nextLine();
                String[] parts = command.split(" ");
                String action = parts[0];
                String description;
                double amount;
                int id;

                switch (action) {
                    case "add":
                        description = parts[1];
                        amount = Double.parseDouble(parts[2]);
                        tracker.addExpense(description, amount);
                        break;
                    case "update":
                        id = Integer.parseInt(parts[1]);
                        description = parts[2];
                        amount = Double.parseDouble(parts[3]);
                        tracker.updateExpense(id, description, amount);
                        break;
                    case "delete":
                        id = Integer.parseInt(parts[1]);
                        tracker.deleteExpense(id);
                        break;
                    case "list":
                        tracker.listExpenses();
                        break;
                    case "summary":
                        if (parts.length > 1 && parts[1].equals("--month")) {
                            System.out.print("Enter month (1-12): ");
                            int month = Integer.parseInt(scanner.nextLine());
                            tracker.summaryByMonth(month);
                        } else {
                            tracker.summary();
                        }
                        break;
                    case "help":
                        printHelp();
                        break;
                    case "exit":
                        System.out.println("Exiting...");
                        return;
                    default:
                        System.out.println("Unknown command. Type 'help' for a list of commands.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please enter a valid number.");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Invalid command format. Please check the command and try again.");
            }
        }
    }

    private static void printHelp() {
        System.out.println("Available commands:");
        System.out.println("add <description> <amount> - Add an expense");
        System.out.println("update <id> <description> <amount> - Update an expense");
        System.out.println("delete <id> - Delete an expense");
        System.out.println("list - List all expenses");
        System.out.println("summary - Get a summary of all expenses");
        System.out.println("summary --month <month> - Get a summary of expenses for a specific month");
        System.out.println("quit - To exit the program");
        System.out.println("help - Show this help message");
    }
}