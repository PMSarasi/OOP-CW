import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserManager {
    private List<User> users; // List to manage users in memory

    // Constructor: Loads users from the file during initialization
    public UserManager() {
        this.users = FileHandler.loadUsersFromFile(); // Load users from the file via FileHandler
    }

    // Register a new user
    public boolean registerUser(String username, String password, List<String> preferences) {
        // Check if username already exists
        if (getUserByUsername(username) != null) {
            System.out.println("Username is already taken: " + username);
            return false;
        }

        // Create a new user object and add to the list
        User newUser = new User(username, password, preferences);
        users.add(newUser);
        FileHandler.addUser(newUser); // Save user to file via FileHandler
        System.out.println("User registered successfully: " + username);
        return true;
    }

    // Login a user by username and password
    public User loginUser(String username, String password) {
        User user = getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful: " + username);
            return user;
        }
        System.out.println("Invalid credentials for username: " + username);
        return null;
    }

    // Get user by username
    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        return null; // Return null if user is not found
    }

    // Get user by index (userId)
    public User getUserById(int userId) {
        if (userId >= 0 && userId < users.size()) {
            return users.get(userId);
        }
        return null; // Return null if userId is invalid
    }

    // List all users
    public void listAllUsers() {
        if (users.isEmpty()) {
            System.out.println("No users available.");
        } else {
            System.out.println("Registered Users:");
            for (User user : users) {
                System.out.println("- " + user);
            }
        }
    }
}
