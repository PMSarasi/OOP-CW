import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {

    private static final String USER_FILE = "users.txt"; // File to store users
    private static final String ARTICLE_FILE = "articles.txt"; // File to store articles

    // Add user to the file
    public static void addUser(User user) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(USER_FILE, true))) {
            String preferences = String.join(";", user.getPreferences());  // Convert preferences list to a semicolon-separated string
            writer.write(user.getUsername() + "," + user.getPassword() + "," + preferences);
            writer.newLine();
            System.out.println("User added to the file: " + user.getUsername());
        } catch (IOException e) {
            System.err.println("Error adding user to file: " + e.getMessage());
        }
    }

    // Save a list of articles to the file
    public static void saveArticles(List<Article> articles) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARTICLE_FILE))) {
            for (Article article : articles) {
                writer.write(article.toFileFormat());  // Use Article's toFileFormat method to convert the object to a CSV-like string
                writer.newLine();
            }
            System.out.println("Articles saved to file.");
        } catch (IOException e) {
            System.err.println("Error saving articles to file: " + e.getMessage());
        }
    }

    // Get and display all articles from the file
    public static void getArticles() {
        try (BufferedReader reader = new BufferedReader(new FileReader(ARTICLE_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int articleId = Integer.parseInt(parts[0]);
                String link = parts[1];
                String title = parts[2];
                String description = parts[3];
                String category = parts[4];
                int rating = Integer.parseInt(parts[5]);

                Article article = new Article(articleId, link, title, description, category);
                article.addRating(rating);  // Set the rating from the file

                // Display the article details
                article.displayArticle();
                System.out.println("-------------------");
            }
        } catch (IOException e) {
            System.err.println("Error retrieving articles from file: " + e.getMessage());
        }
    }

    // Load articles from the file into a List
    public static List<Article> loadArticlesFromFile(String filename) {
        List<Article> articles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int articleId = Integer.parseInt(parts[0]);
                String link = parts[1];
                String title = parts[2];
                String description = parts[3];
                String category = parts[4];
                int rating = Integer.parseInt(parts[5]);

                Article article = new Article(articleId, link, title, description, category);
                article.addRating(rating);  // Set the rating from the file
                articles.add(article);
            }
        } catch (IOException e) {
            System.err.println("Error loading articles from file: " + e.getMessage());
        }
        return articles;
    }

    // Load users from the file
    public static List<User> loadUsersFromFile() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(USER_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String username = parts[0];
                String password = parts[1];
                List<String> preferences = List.of(parts[2].split(","));
                users.add(new User(username, password, preferences));
            }
        } catch (IOException e) {
            System.err.println("Error loading users from file: " + e.getMessage());
        }
        return users;
    }
}
