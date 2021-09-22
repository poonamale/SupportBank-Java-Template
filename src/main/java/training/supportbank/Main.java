package training.supportbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {

    private static boolean checkUserExists(List<UserAccount> accountList , String name){
        boolean doesUserExist = false;
        for (int i = 0; i < accountList.size(); i++) {
            if( accountList.get(i).getUserName().equals(name) ) {
                doesUserExist = true;
            }
        }
        return doesUserExist;
    }

    private static void updateBalance(List<UserAccount> accountList, String sender, String receiver, BigDecimal amount){
        for (int i = 0; i < accountList.size(); i++) {
            if((accountList.get(i).getUserName()).equals(sender)){
                accountList.get(i).increaseBalance(amount);
            }
            else if((accountList.get(i).getUserName()).equals(receiver)){
                accountList.get(i).decreaseBalance(amount);
            }
        }
    }

    public static void main(String args[]) {
        String path = "/Users/pale3/Downloads/Transactions2014.csv";
        String line = "";
        try {
            BufferedReader br = new BufferedReader( new FileReader(path));

            int iteration = 0;
            List<UserAccount> accountList = new ArrayList<>();
            List<Transaction> transactionList = new ArrayList<>();
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
                }

                if (!checkUserExists(accountList, receiver)){
                    UserAccount person = new UserAccount(receiver);
                    accountList.add(person);
                }

                transactionList.add( new Transaction(date, sender, receiver, narrative, amount));
                updateBalance(accountList, sender, receiver, amount);



            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }




}
