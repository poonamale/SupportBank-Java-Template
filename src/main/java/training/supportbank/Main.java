package training.supportbank;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class Main {

    public static void main(String args[]) {
        // Your code here!
        String path = "/Users/pale3/Downloads/Transactions2014.csv";
        String line = "";

        try {
            BufferedReader br = new BufferedReader( new FileReader(path));

            int iteration = 0;
            while((line = br.readLine()) != null) {
                //we want to skip the first line of csv to skip "Amount" into BigDecimal
                if (iteration == 0 ) {
                    iteration++;
                    continue;
                }
                // changing the data into an array
                String[] values = line.split(",");
                Transaction aTransaction = new Transaction(values[0], values[1], values[2], values[3], values[4]);
                System.out.println(aTransaction);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
