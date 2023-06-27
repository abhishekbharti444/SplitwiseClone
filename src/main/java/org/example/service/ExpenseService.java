package org.example.service;

import org.example.model.*;

import java.util.List;

public class ExpenseService {

    public static Expense createExpense(ExpenseType expenseType, double amount, User paidBy, List<Split> splits, ExpenseMetadata expenseMetadata) {
        switch (expenseType) {
            case EQUAL:

                return new EqualExpense(amount, paidBy, expenseMetadata, splits);
            case EXACT:
                // TODO: Calculate the individual split and set the amount.
                return new ExactExpense(amount, paidBy, expenseMetadata, splits);
            case PERCENT:
                // TODO: Calculate the individual percentages of each of the splits and set the amount of each of the split accordingly;
                return new PercentExpense(amount, paidBy, expenseMetadata, splits);
            default:
                return null;
        }
    }
}
