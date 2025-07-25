package model;
import java.time.LocalDate;
public class Transaction {
    private TransactionType type;
    private Category category;
    private double amount;
    private LocalDate date;
    private String description;

    public Transaction(TransactionType type, Category category, double amount, LocalDate date, String decription) {
        this.type = type;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public TransactionType getType() {
    return type; }
    public Category getCategory() {
        return category; }
    public double getAmount() {
        return amount; }
    public LocalDate getDate() {
        return date; }
    public String getDescription() {
        return description; }

    @Override
    public String toString() {
        return String.format("[%s] %-10s $%-8.2f %-15s (%s)",
                date, type, amount, category, description);
    }
}
