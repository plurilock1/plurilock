package group1.cpsc319.plurilock_client.Model;

/**
 * Created by anneunjungkim on 2016-03-07.
 */
public class Transaction {
    private String date;
    private String info;
    private String amount;

    public Transaction(String date, String info, String amount) {
        this.date = date;
        this.info = info;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public String getInfo() {
        return info;
    }

    public String getAmount() {
        return amount;
    }
}
