package org.example.model;

import java.util.List;

public class ExactExpense extends Expense {
    public ExactExpense(double amount, User paidBy, ExpenseMetadata expenseMetadata, List<Split> splits) {
        super(amount, paidBy, expenseMetadata, splits);
    }

    @Override
    public boolean validate() {

        // TODO: Write the logic for validation for exact expense case


        return false;
    }
}
