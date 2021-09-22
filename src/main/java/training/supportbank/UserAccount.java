package training.supportbank;

import java.math.BigDecimal;

public class UserAccount {
    private String userName;
    private BigDecimal balance;

    public UserAccount (String name){
        userName = name;
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
}
