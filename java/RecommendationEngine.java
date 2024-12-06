import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RecommendationEngine {

    // Method to get recommendations based on username
    public List<Article> getRecommendations(String username) {
        UserManager userManager = new UserManager(); // UserManager instance
        User user = userManager.getUserByUsername(username); // Get user by username

        if (user == null) {
            System.out.println("User not found with username: " + username);
            return new ArrayList<>();
        }

        // Fetch all articles from the file and generate recommendations
        List<Article> allArticles = Article.loadArticlesFromFile("articles.txt"); // Load articles from file
        return recommendArticles(user, allArticles);
    }

    // Simple recommendation algorithm
    public List<Article> recommendArticles(User user, List<Article> allArticles) {
        List<Article> recommendations = new ArrayList<>();

        // Iterate through all articles and match with user preferences
        for (Article article : allArticles) {
            for (String preference : user.getPreferences()) {
                // Recommend articles that match user's preferences by category
                if (article.getCategory().equalsIgnoreCase(preference.trim())) {
                    recommendations.add(article);
                    break; // Avoid adding the same article multiple times
                }
            }
        }

        return recommendations;
    }
}
