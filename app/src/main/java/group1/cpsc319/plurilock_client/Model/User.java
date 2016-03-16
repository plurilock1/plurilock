package group1.cpsc319.plurilock_client.Model;

/**
 * Created by Matas on 2/21/16.
 */
public class User {
    private String name;
    private String email;

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String userMessage() {
        return "Hello, " + this.name + "! " + "Is your email, " + this.email + " still valid?";
    }
}
