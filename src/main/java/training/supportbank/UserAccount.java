package training.supportbank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class UserAccount {
    private String userName;
    private BigDecimal balance;

    public UserAccount (String name){
        userName = name;
        balance = new BigDecimal(0);
    }

    public void decreaseBalance(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public void increaseBalance(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public String getUserName() {
        return userName;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userName='" + userName + '\'' +
                ", balance=" + balance +
                '}';
    }
}
