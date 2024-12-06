

import java.util.List;

public class User {
    private String username;
    private String password;
    private List<String> preferences;

    // Constructor
    public User(String username, String password, List<String> preferences) {
        this.username = username;
        this.password = password;
        this.preferences = preferences;
    }

    // Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getPreferences() {
        return preferences;
    }

    // Override toString
    @Override
    public String toString() {
        return "Username: " + username + ", Preferences: " + preferences;
    }
}

