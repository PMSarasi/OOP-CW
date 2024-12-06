import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleManager {
    private List<Article> articles = new ArrayList<>(); // List to hold articles

    // Constructor: Fetch articles from a text file (instead of a database)
    public ArticleManager() {
        // Assuming "articles.txt" is the file where articles are stored
        articles = Article.loadArticlesFromFile("articles.txt");  // Load articles from text file
    }

    // Method to get the list of all articles
    public List<Article> listAllArticles() {
        if (articles.isEmpty()) {
            System.out.println("No articles available.");
        } else {
            System.out.println("Displaying all articles:");
            // Loop through each article and display its details
            for (Article article : articles) {
                article.displayArticle(); // Display article using the displayArticle method in Article class
            }
        }
        return articles; // Return the list of articles
    }

    // Method to rate an article
    public void rateArticle(int userId, int articleId, int rating) {
        // Loop through articles and find the article with the specified articleId
        for (Article article : articles) {
            if (article.getArticleId() == articleId) {
                article.addRating(rating); // Add rating to the article
                System.out.println("Article rated successfully!");
                // Update the article in the text file after rating
                Article.saveArticlesToFile(articles, "articles.txt");
                return;
            }
        }
        System.out.println("Article not found.");
    }

    // Method to get an article by its ID
    public Article getArticleById(int articleId) {
        for (Article article : articles) {
            if (article.getArticleId() == articleId) {
                return article; // Return the article if found
            }
        }
        return null; // Return null if article is not found
    }

    // Method to recommend articles based on user preferences (you can modify this as needed)
    public List<Article> recommendArticles(User user) {
        List<Article> recommendations = new ArrayList<>();
        // Example logic: Filter articles based on user preferences
        for (Article article : articles) {
            if (user.getPreferences().contains(article.getCategory())) {
                recommendations.add(article); // Add article if it matches user preferences
            }
        }
        return recommendations;
    }
}
