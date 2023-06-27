package org.example.model;

import java.util.List;

public class EqualExpense extends Expense {
    public EqualExpense(double amount, User paidBy, ExpenseMetadata expenseMetadata, List<Split> splits) {
        super(amount, paidBy, expenseMetadata, splits);
    }

    @Override
    public boolean validate() {

        // TODO: Write the logic for validation in equalExpense case


        return true;
    }
}
