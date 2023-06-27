package org.example.model;

import java.util.List;

public class PercentExpense extends Expense {
    public PercentExpense(double amount, User paidBy, ExpenseMetadata expenseMetadata, List<Split> splits) {
        super(amount, paidBy, expenseMetadata, splits);
    }

    @Override
    public boolean validate() {

        // TODO: Write the logic for validation for percentExpense case

        return false;
    }
}
