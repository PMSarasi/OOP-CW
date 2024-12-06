import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserManager userManager = new UserManager(); // UserManager now loads users via FileHandler
        ArticleManager articleManager = new ArticleManager();
        RecommendationEngine recommendationEngine = new RecommendationEngine();
        Scanner scanner = new Scanner(System.in);

        // Load articles and users from the file system at the start
        List<Article> articles = FileHandler.loadArticlesFromFile("articles.txt"); // Use FileHandler to load articles
        List<User> users = FileHandler.loadUsersFromFile(); // Use FileHandler to load users

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Register User");
            System.out.println("2. Login User");
            System.out.println("3. List All Users");
            System.out.println("4. View Articles");
            System.out.println("5. Rate Article");
            System.out.println("6. Get Recommendations");
            System.out.println("7. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1: // Register User
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();
                    System.out.print("Enter preferences (comma-separated): ");
                    String preferencesInput = scanner.nextLine();
                    userManager.registerUser(username, password, List.of(preferencesInput.split(",")));
                    break;

                case 2: // Login User
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    userManager.loginUser(loginUsername, loginPassword);
                    break;

                case 3: // List All Users
                    userManager.listAllUsers();
                    break;

                case 4: // View Articles
                    articleManager.listAllArticles(); // Display articles
                    break;

                case 5: // Rate Article
                    System.out.print("Enter user ID: ");
                    int userIdForRating = scanner.nextInt();
                    System.out.print("Enter article ID: ");
                    int articleIdForRating = scanner.nextInt();
                    System.out.print("Enter rating (1-5): ");
                    int rating = scanner.nextInt();
                    articleManager.rateArticle(userIdForRating, articleIdForRating, rating);
                    break;

                case 6: // Get Recommendations
                    System.out.print("Enter username for recommendations: ");
                    String usernameForRecommendation = scanner.nextLine();

                    // Fetch recommended articles based on user preferences
                    List<Article> recommendedArticles = recommendationEngine.getRecommendations(usernameForRecommendation);

                    // Display recommendations or fallback message
                    if (recommendedArticles.isEmpty()) {
                        System.out.println("No recommendations available for the specified username.");
                    } else {
                        System.out.println("\nRecommended Articles:");
                        System.out.println("---------------------");
                        for (Article article : recommendedArticles) {
                            article.displayArticle(); // Assuming Article has a displayArticle method
                            System.out.println("---------------------");
                        }
                    }
                    break;

                case 7: // Exit
                    System.out.println("Exiting program. Goodbye!");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
