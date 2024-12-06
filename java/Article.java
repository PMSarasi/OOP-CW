import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Article {
    private final int articleId;
    private final String link;
    private final String title;
    private final String description;
    private final String category;
    private int rating;

    // Constructor with all parameters
    public Article(int articleId, String link, String title, String description, String category) {
        this.articleId = articleId;
        this.link = link;
        this.title = title;
        this.description = description;
        this.category = category;
        this.rating = 0; // Initialize rating to 0
    }

    // Constructor that initializes the article from a ResultSet
    public Article(ResultSet rs) throws SQLException {
        this.articleId = rs.getInt("article_id");
        this.link = rs.getString("link");
        this.title = rs.getString("title");
        this.description = rs.getString("description");
        this.category = rs.getString("category");
        this.rating = 0; // Default rating
    }

    // Getters for each field
    public int getArticleId() {
        return articleId;
    }

    public String getLink() {
        return link;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    // Method to add rating to the article
    public void addRating(int rating) {
        this.rating = rating;
    }

    // Method to get the current rating
    public int getRating() {
        return rating;
    }

    // Method to display article details
    public void displayArticle() {
        System.out.println("Article ID: " + articleId);
        System.out.println("Link: " + link);
        System.out.println("Title: " + title);
        System.out.println("Description: " + description);
        System.out.println("Category: " + category);
        System.out.println("Rating: " + rating);
    }

    // Method to generate a string representation of the article for file storage
    public String toFileFormat() {
        return articleId + "," + link + "," + title + "," + description + "," + category + "," + rating;
    }

    // Static method to load articles from a text file
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
                article.addRating(rating);  // Set the rating
                articles.add(article);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return articles;
    }

    // Static method to save articles to a text file
    public static void saveArticlesToFile(List<Article> articles, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (Article article : articles) {
                writer.write(article.toFileFormat());
                writer.newLine();  // Add a new line after each article
            }
            System.out.println("Articles saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
