
/*
 * Micah Alummoottil 4/9/25 section 500
 * 
 * For full credit, comment any completed code to show your understanding.
 * For full credit, comment any code you complete to show your understanding.
 */


 import java.io.*;
 import java.util.*;
 import java.util.concurrent.locks.Lock;
 import java.util.concurrent.locks.ReentrantLock;
 import java.io.File; 
import java.io.FileNotFoundException;
import java.security.spec.ECFieldF2m;
 
 // Exception for insufficient funds
 class InsufficientFundsException extends Exception {
     //complete the custon exception
    public InsufficientFundsException(String message) {
        super(message); //pass in our message and call super for extending exception
    }
 }
 
 // BankAccount Interface
 interface BankAccount {
     void deposit(double amount); //deposit
     void withdraw(double amount) throws InsufficientFundsException; //withdraw that might throw our custom exception
     double getBalance(); //getter for balance
     int getAccountNumber(); //getter for acc num
     Lock getLock(); //getter for lock
 }
 
 // Abstract Account Class
 abstract class AbstractBankAccount implements BankAccount {
     protected int accountNumber; // acc num
     protected double balance; //balance for the account
     protected final Lock lock = new ReentrantLock(); //lock to do for transactions
 
     public AbstractBankAccount(int accountNumber, double balance) {
         //complete this constructor
         this.accountNumber = accountNumber;
         this.balance = balance; //set variables
     }
 
     public void deposit(double amount) {
        //complete this required method, preventing multiple simultaneous uses
        lock.lock(); //first lock it, then perform action, then unlock
        balance += amount;
        lock.unlock();

     }
 
     public void withdraw(double amount) throws InsufficientFundsException {
         //complete this required method, preventing multiple simultaneous uses.
         try {
            lock.lock();
            if (amount > balance) {
                throw new InsufficientFundsException("Not enough funds for withdrawal.");
            } //if they withdraw more than they have throw error
            else {
                balance -= amount; //lock, perform, unlock
            }
         }
         finally { //always make sure to unlock it even if we throw exception
            lock.unlock();
         }
     }

     public double getBalance() {
        return balance; //getter
     }

     public int getAccountNumber() {
        return accountNumber; //getter
     }

     public Lock getLock() {
        return lock; //getter for lock
     }
 
 // creat other methods as needed.
 }
 
 // Checking Account Class
 class CheckingAccount extends AbstractBankAccount {
     public CheckingAccount(int accountNumber, double balance) {
         super(accountNumber, balance); //this checking account class takes in our acc num and balance
     } //creates a new account and uses super for the other methods like deposit, withdraw, etc
 }
 
 // Savings Account Class
 class SavingsAccount extends AbstractBankAccount {
     public SavingsAccount(int accountNumber, double balance) {
         super(accountNumber, balance);//this savings account class takes in our acc num and balance
         //creates a new account and uses super for the other methods like deposit, withdraw, etc
     }
 }
 
 // Generic Bank Class
 class Bank<T extends BankAccount> {
     private final Map<Integer, T> accounts = new HashMap<Integer, T>();// chose appropriate collection
 
     public void addAccount(T account) {
         //complete this method
         accounts.put(account.getAccountNumber(), account); //this will put our account into the accounts hashmap     
     }
 
     public T getAccount(int accountNumber) {
         //complete this method
         return accounts.get(accountNumber); //getter for the account with that accNum
     }

     public Collection<T> getAllAccounts() { //custom method to get all accounts so we can print the balances
        return accounts.values();
     }

 }
 
 // Transaction Runnable
 class Transaction implements Runnable {
     private final Bank<? extends BankAccount> bank;
     private final String type;
     private final int sourceAccount; //our attributes needed for a transaction
     private final int targetAccount;
     private final double amount;
 
     public Transaction(Bank<? extends BankAccount> bank, String type, int sourceAccount, int targetAccount, double amount) {
         this.bank = bank;
         this.type = type;
         this.sourceAccount = sourceAccount; //constructor
         this.targetAccount = targetAccount;
         this.amount = amount;
     }
 
     @Override
     public void run() {
        BankAccount account;
        BankAccount from;
        BankAccount to; //create bank accounts for different cases
         try {
             switch (type) {
                case "deposit":
                    account = bank.getAccount(sourceAccount); //get account then deposit onto that one
                    System.out.println("Attempting to deposit $" + amount + " to " + account.getAccountNumber());
                    account.deposit(amount);
                    System.out.println("Deposit Transaction Successful");
                    break;
                case "withdraw":
                    account = bank.getAccount(sourceAccount); //get account then weithdraw from that one
                    System.out.println("Attempting to withdraw $" + amount + " from " + account.getAccountNumber());
                    account.withdraw(amount);
                    System.out.println("Withdraw Transaction Successful");
                    break;
                case "transfer":
                    from = bank.getAccount(sourceAccount); //get from and to then call transfer 
                    to = bank.getAccount(targetAccount);
                    System.out.println("Attempting to transfer $" + amount + " from " + from.getAccountNumber() + " to " + to.getAccountNumber());
                    transfer(from, to, amount);
                    System.out.println("Transfer Transaction Successful");
                    break;
                case "deadlock_demo":
                    from = bank.getAccount(sourceAccount); //get from and to then call deadlock
                    to = bank.getAccount(targetAccount);
                    System.out.println("Attempting $" + amount + " deadlock with " + from.getAccountNumber() + " and " + to.getAccountNumber());
                    deadlockDemo(from, to, amount);
                    System.out.println("Deadlock Transaction Successful");
                    break;
                case "race_condition_demo":
                    from = bank.getAccount(sourceAccount); //get from and to then call race cond
                    to = bank.getAccount(targetAccount);
                    System.out.println("Attempting $" + amount + " race with " + from.getAccountNumber() + " and " + to.getAccountNumber());
                    raceConditionDemo(from, to, amount);
                    System.out.println("Race Transaction Successful");
                    break;
                 //complete the switch statement to handle all transaction types.
             }
         } catch (InsufficientFundsException e) {
             System.out.println("Transaction failed: " + e.getMessage()); //catch the exception and print msg
         }
     }
 
     private void transfer(BankAccount from, BankAccount to, double amount) throws InsufficientFundsException {
         // complete the transfer method. Make sure multiple requests cannot cause a problem.
         //handle the "InsufficientFundsException" without the program halting.
         Lock first = null; //create first and second locks
         Lock second = null;
         if (from.getAccountNumber() < to.getAccountNumber()) { //we will choose the lower acc num to lock first
            first = from.getLock();
            second = to.getLock();
        }
        else {
            first = to.getLock(); // if 2nd is lower then get that lock for first
            second = from.getLock();
        }
         try {
            first.lock(); // try locking both
            second.lock();

            from.withdraw(amount); //try doing the transfer
            to.deposit(amount);

         }
         catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage()); //catch error and print msg
         }
         finally { //always make sure to unlock them
            second.unlock();
            first.unlock();
         }
     }
 
     private void deadlockDemo(BankAccount from, BankAccount to, double amount) {
         //Start a thread for 2 transactions, one in each direction
         //transfer (from, to, amount);
         //transfer (to, from, amount): at the same time.
         //solve the deadlock condition.

        //create 2 threads doing transfers in opposite ways
         Runnable transfer1 = new Runnable() {
            @Override //override our run func
            public void run() {
                try {
                    transfer(from, to, amount); //try creating thread, catch error if needed
                }
                catch (InsufficientFundsException e) {
                    System.out.println("Transfer failed: " + e.getMessage()); //catch error
                }
            }
         };
         Runnable transfer2 = new Runnable() {
            @Override
            public void run() { //ovverride our run func
                try {
                    transfer(to, from, amount); //try creating thread, catch error if needed
                }
                catch (InsufficientFundsException e) {
                    System.out.println("Transfer failed: " + e.getMessage()); //catch error
                }
            }
         };
         Thread thread1 = new Thread(transfer1); //create threads
         Thread thread2 = new Thread(transfer2);

         thread1.start(); //start both at same time
         thread2.start();
         try {
            thread1.join(); //join them to wait until they finish
            thread2.join();
         }
         catch (Exception e) {
            System.out.println("Cannot join thread"); //throw error in case
         }
     }
 
     private void raceConditionDemo(BankAccount from, BankAccount to, double amount) {
         
         //start a thread for each tranaction twice,
         // withdraw (from, amount);
         // withdraw (from, amount);
         //deposit (to, amount);
         // and deposit (to, amount); at the same time.
         // solve the race condition.

        Runnable withdraw = new Runnable() {
            @Override
            public void run() {
                try {
                    from.withdraw(amount); //ovveride the run 
                    //do withdraw
                }
                catch (InsufficientFundsException e) {
                    System.out.println("Transfer failed: " + e.getMessage()); //catch error if needed
                }
            }
        };

        Runnable deposit = new Runnable() {
            @Override
            public void run() { //ovverride the run
                to.deposit(amount); //try deposit
            }
        };

        Thread thread1 = new Thread(withdraw); //create thread for each transaction twice
        Thread thread2 = new Thread(withdraw);
        Thread thread3 = new Thread(deposit);
        Thread thread4 = new Thread(deposit);

         thread1.start(); //start at same time
         thread2.start();
         thread3.start();
         thread4.start();

         try { //try joining them to wait until they finish
            thread1.join();
            thread2.join();
            thread3.join();
            thread4.join();
         }
         catch (Exception e) { //catch exception
            System.out.println("Cannot join threads");
         }
     }
 }
 
 // Main Class
 public class BankSystem {
     // you do not need to change the main method, or add any additional
     //methods to this BankSystem class.
     public static void main(String[] args)  {
         Bank<BankAccount> bank = new Bank<>(); //intialize bank
         loadAccounts(bank, "accounts.txt"); //load accs
         System.out.println("Bank Account balances before transactions");
         for (BankAccount acc : bank.getAllAccounts()) { //print before transactions
            System.out.println(acc.getAccountNumber() + ": $" + acc.getBalance());
        }
         executeTransactions(bank, "transactions.txt"); //process transactions
         System.out.println("Bank account balances after transactions");
         for (BankAccount acc : bank.getAllAccounts()) { //print after transactions
            System.out.println(acc.getAccountNumber() + ": $" + acc.getBalance());
        }
     }
 
     private static void loadAccounts(Bank<BankAccount> bank, String filename)  {
         //todo Reading and load all the accounts. These will go in an abstract
         //abstract bank account, so they must be in some form of collection. What is the 
         //best type for bank accounts?
        try {
            File accFile = new File("accounts.txt"); //create file and scanner
            Scanner scanner = new Scanner(accFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] values = data.split(","); //parse the data into values
                int accNum = Integer.parseInt(values[0]);
                double balance = Double.parseDouble(values[2]);
                if (values[1].equals("Checking")) { //check if its a checking or savings
                    bank.addAccount(new CheckingAccount(accNum, balance));
                }
                else if (values[1].equals("Savings")) {
                    bank.addAccount(new SavingsAccount(accNum, balance));
                }
            }
            scanner.close(); //close scanner
        }
        catch (FileNotFoundException e) { //catch error if file not found
            System.out.println("Error");
        }
     }
 
     private static void executeTransactions(Bank<BankAccount> bank, String filename)  {
         //todo Read the transaction account, as you read each transaction, start a new 
         //transaction thread. These transactions will start while others
         //are running, and may finish at any time.
         try {
            File transFile = new File("transactions.txt"); //create file and scanner
            Scanner scanner = new Scanner(transFile);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] values = data.split(","); //parse data into values
                String type = values[0];
                int sourceAccount = Integer.parseInt(values[1]);
                int targetAccount = -1; //intialize target acc to -1
                if (!type.equals("deposit") && !type.equals("withdraw")) { //if its a transaction that has a target acc. then update it
                   targetAccount = Integer.parseInt(values[2]);
                }
                double amount = Double.parseDouble(values[3]);
                Thread thread = new Thread(new Transaction(bank, type, sourceAccount, targetAccount, amount)); //create a new thread for that transaction
                thread.start(); //start thread for each line we process
                try {
                    thread.join();
                }
                catch (Exception e) {
                    System.out.println("Cannot join thread");
                }
            }
            scanner.close(); //close scanner 
         }
         catch (FileNotFoundException e) { //file not found error
            System.out.println("Error");
         }

    }
 }