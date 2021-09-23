package training.supportbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;


public class Main {

    private static final Pattern COMMAND_PATTERN = Pattern.compile("^List (.*)$"
            , Pattern.CASE_INSENSITIVE);
    private static Object Transaction;

    private static void printBanner() {
        System.out.println("Welcome to SupportBank!");
        System.out.println();
        System.out.println("Please enter the number corresponding to your request:");
        System.out.println(" List All - list all account balances");
        System.out.println(" List [Account] - list transactions for the specified account");
        System.out.println(" Quit application");
        System.out.println();
    }

    public static void executeCommand(List<Transaction> transactionList, String command) {
        if (command.equalsIgnoreCase("quit")) {
            System.out.println("Goodbye.");
            System.exit(0);
        }
        if (command.equalsIgnoreCase("all")) {
                listAllAccounts();
            }
        else { for (Transaction eachTransaction : transactionList){
                String fromName = eachTransaction.getFrom();
                String toName = eachTransaction.getTo();
                if (command.equalsIgnoreCase(toName) || command.equalsIgnoreCase(fromName)){
                    System.out.println(eachTransaction.getFrom() + " gave " + eachTransaction.getTo() + " £" + eachTransaction.getAmount() + " for " + eachTransaction.getNarrative());
                }
            }
        }
    }


    static private void listAllAccounts(){
        System.out.println("request List All");
        System.out.printf("we have %d",  accountMap.size());
        for (UserAccount account : accountMap.values()) {
            System.out.println("  " + account.toString() );
        }
        System.out.println();

    }


    private static boolean checkUserExists(List<UserAccount> accountList , String name){
        boolean doesUserExist = false;
        for (int i = 0; i < accountList.size(); i++) {
            if( accountList.get(i).getUserName().equals(name) ) {
                doesUserExist = true;
            }
        }
        return doesUserExist;
    }

    static HashMap<String, UserAccount> accountMap = new HashMap<>();

    private static void updateBalance(List<UserAccount> accountList, String sender, String receiver, BigDecimal amount){
        accountMap.get(sender).decreaseBalance(amount);
        accountMap.get(receiver).increaseBalance(amount);
    }

    public static void main(String args[]) {

        String path = "/Users/pale3/Downloads/Transactions2014.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader( new FileReader(path));

            List<UserAccount> accountList = new ArrayList<>();
            List<Transaction> transactionList = new ArrayList<>();

            int iteration = 0;
            while((line = br.readLine()) != null) {
                //we want to skip the first line of csv to skip "Amount" into BigDecimal
                if (iteration == 0 ) {
                    iteration++;
                    continue;
                }
                // changing the data into an array
                String[] values = line.split(",");
                String date = values[0];
                String sender = values[1];
                String receiver = values[2];
                String narrative = values[3];
                String amount = (values[4]);

                if (!checkUserExists(accountList, sender)){
                    UserAccount person = new UserAccount(sender);
                    accountList.add(person);
                    accountMap.put(sender, person);
                }

                if (!checkUserExists(accountList, receiver)){
                    UserAccount person = new UserAccount(receiver);
                    accountList.add(person);
                    accountMap.put(receiver, person);
                }

                transactionList.add( new Transaction(date, sender, receiver, narrative, amount));
                updateBalance(accountList, sender, receiver, new BigDecimal(amount));

            }
            //System.out.println(accountList);

            printBanner();

            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String command = scanner.nextLine();
                executeCommand(transactionList, command);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
