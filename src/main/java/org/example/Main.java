package org.example;

import org.example.model.*;
import org.example.repository.ExpenseManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    /*
        Create an expense sharing application.

        An expense sharing application is where you can add your expenses and split it among different people. The app keeps balances between people as in who owes how much to whom.

        Example

        You live with 3 other friends.

        You: User1 (id: u1)

        Flatmates: User2 (u2), User3 (u3), User4 (u4)

        ---

        This month's electricity bill was Rs. 1000.

        Now you can just go to the app and add that you paid 1000,

        select all the 4 people and then select split equally.

        Input: u1 1000 4 u1 u2 u3 u4 EQUAL

        For this transaction, everyone owes 250 to User1.

        The app should update the balances in each of the profiles accordingly. User2 owes User1: 250 (0+250)

        User3 owes User1: 250 (0+250)

        User4 owes User1: 250 (0+250)

        ---

        Now, It is the BBD sale on Flipkart and there is an offer on your card.

        You buy a few stuffs for User2 and User3 as they asked you to.

        The total amount for each person is different.

        Input: u1 1250 2 u2 u3 EXACT 370 880

        For this transaction, User2 owes 370 to User1 and User3 owes 880 to User1.

        The app should update the balances in each of the profiles accordingly.

        User2 owes User1: 620 (250+370)

        User3 owes User1: 1130 (250+880)

        User4 owes User1: 250 (250+0)

        ---

        Now, you go out with your flatmates and take your brother/sister along with you.

        User4 pays and everyone splits equally. You owe for 2 people.

        Input: u4 1200 4 u1 u2 u3 u4 PERCENT 40 20 20 20

        For this transaction, User1 owes 480 to User4, User2 owes 240 to User4 and User3 owes 240 to User4.

        The app should update the balances in each of the profiles accordingly.

        User1 owes User4: 230 (250-480)

        User2 owes User1: 620 (620+0)

        User2 owes User4: 240 (0+240)

        User3 owes User1: 1130 (1130+0)

        User3 owes User4: 240 (0+240)

        Requirements

        User: Each user should have a userId, name, email, mobile number.

        Expense: Could either be EQUAL, EXACT or PERCENT

        Users can add any amount, select any type of expense and split with any of the available users.

        The percent and amount provided could have decimals upto two decimal places.

        In case of percent, you need to verify if the total sum of percentage shares is 100 or not.

        In case of exact, you need to verify if the total sum of shares is equal to the total amount or not.

        The application should have a capability to show expenses for a single user as well as balances for everyone.

        When asked to show balances, the application should show balances of a user with all the users where there is a non-zero balance.

        The amount should be rounded off to two decimal places. Say if User1 paid 100 and amount is split equally among 3 people. Assign 33.34 to first person and 33.33 to others.

        Input

        You can create a few users in your main method. No need to take it as input.

        There will be 3 types of input:

        Expense in the format: EXPENSE <user-id-of-person-who-paid> <no-of-users> <space-separated-list-of-users> <EQUAL/EXACT/PERCENT> <space-separated-values-in-case-of-non-equal>

        Show balances for all: SHOW

        Show balances for a single user: SHOW <user-id>

        Output

        When asked to show balance for a single user. Show all the balances that user is part of:

        Format: <user-id-of-x> owes <user-id-of-y>: <amount>

        If there are no balances for the input, print No balances

        In cases where the user for which balance was asked for, owes money, they’ll be x. They’ll be y otherwise.

        Optional Requirements

        - A way to add an expense name while adding the expense. Can also add notes, images, etc.

        - Option to split by share. Ex: ‘User4 pays and everyone splits equally. You pay for 2 people.’ could be added as: u4 1200 4 u1 u2 u3 u4 SHARE 2 1 1 1

        - A way to show the passbook for a user. The entries should show all the transactions a user was part of. You can print in any format you like.

        - There can be an option to simplify expenses. When simplify expenses is turned on (is true), the balances should get simplified. Ex: ‘User1 owes 250 to User2 and User2 owes 200 to User3’ should simplify to ‘User1 owes 50 to User2 and 200 to User3’.
    */
    public static void main(String[] args) {
        System.out.println("Hello world!");

        /*
         *
         *   Classes:
         *       1. User
         *       2. Split
         *       3. Expense
         *       4. ExpenseMetadata
         *       5.
         *
         * */

        ExpenseManager expenseManager = new ExpenseManager();
        expenseManager.addUser(new User("U1", "Abhishek", "8338864618", "asdflk@cokd.com"));
        expenseManager.addUser(new User("U2", "Angry", "8448864618", "sdfncodfi@ckldfo.com"));
        expenseManager.addUser(new User("U3", "Bittu", "2348923489", "dncijgnla@dlc.com"));
        expenseManager.addUser(new User("U4", "Sonu", "98239430934", "ndfncoiiodj@doc.com"));

        Scanner scanner = new Scanner(System.in);

        while (true) {
            String command = scanner.nextLine();
            String[] commands = command.split(" ");
            String commandType = commands[0];

            switch (commandType) {
                case "SHOW":
                    if (commands.length == 1) {
                        expenseManager.showBalances();
                    } else {
                        expenseManager.showBalance(commands[1]);
                    }
                    break;
                case "EXPENSE":
                    String paidBy = commands[1];
                    Double amount = Double.parseDouble(commands[2]);
                    int noOfUsers = Integer.parseInt(commands[3]);
                    String expenseType = commands[4 + noOfUsers];
                    List<Split> splits = new ArrayList<>();
                    switch (expenseType) {
                        case "EQUAL":
                            for (int i = 0; i < noOfUsers; i++) {
                                splits.add(new EqualSplit(expenseManager.getUserMap().get(commands[4 + i])));
                            }
                            expenseManager.addExpense(ExpenseType.EQUAL, amount, paidBy, splits, null);
                            break;
                        case "EXACT":
                            for (int i = 0; i < noOfUsers; ++i) {
                                splits.add(new ExactSplit(expenseManager.getUserMap().get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
                            }
                            expenseManager.addExpense(ExpenseType.EXACT, amount, paidBy, splits, null);
                            break;
                        case "PERCENT":
                            for (int i = 0; i < noOfUsers; ++i) {
                                splits.add(new PercentSplit(expenseManager.getUserMap().get(commands[4 + i]), Double.parseDouble(commands[5 + noOfUsers + i])));
                            }
                            expenseManager.addExpense(ExpenseType.PERCENT, amount, paidBy, splits, null);
                            break;
                    }
                    break;
            }
        }

    }
}
