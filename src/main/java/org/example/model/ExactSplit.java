package org.example.model;

public class ExactSplit extends Split {

    private double amount;
    public ExactSplit(User user, double amount) {
        super(user);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
