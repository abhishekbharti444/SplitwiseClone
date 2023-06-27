package org.example.model;

import java.util.List;

public abstract class Expense {

    private String id;
    private double amount;
    private User paidBy;
    private ExpenseMetadata expenseMetadata;
    private List<Split> splits;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public ExpenseMetadata getExpenseMetadata() {
        return expenseMetadata;
    }

    public void setExpenseMetadata(ExpenseMetadata expenseMetadata) {
        this.expenseMetadata = expenseMetadata;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    public Expense(double amount, User paidBy, ExpenseMetadata expenseMetadata, List<Split> splits) {
        this.amount = amount;
        this.paidBy = paidBy;
        this.expenseMetadata = expenseMetadata;
        this.splits = splits;
    }

    public abstract boolean validate();
}
