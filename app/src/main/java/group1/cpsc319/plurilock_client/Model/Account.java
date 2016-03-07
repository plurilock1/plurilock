package group1.cpsc319.plurilock_client.Model;

/**
 * Created by anneunjungkim on 2016-03-05.
 */
public class Account {
    private String type;
    private String id;
    private String balance;

    public Account(String type, String id, String balance) {
        this.type = type;
        this.id = id;
        this.balance = balance;
    }

    public String getType() {
        return type;
    }

    public String getID() {
        return id;
    }

    public String getBalance() {
        return balance;
    }
}
