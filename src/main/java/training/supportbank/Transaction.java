package training.supportbank;

import java.math.BigDecimal;

public class Transaction {
    private String date;
    private String from;
    private String to;
    private String narrative;
    private BigDecimal amount;

    public Transaction(String initDate, String initFrom, String initTo, String initNarrative, String initAmount){
        date = initDate;
        from = initFrom;
        to = initTo;
        narrative = initNarrative;
        amount = new BigDecimal(initAmount);
    }

    public String getDate() {
        return date;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getNarrative() {
        return narrative;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String toString(){
        return String.format("On %s, %s gave %s money for %s. The money owed is %.2f",date, from, to, narrative, amount);
    }
}
