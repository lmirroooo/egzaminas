package helpdesk.model;

public class Films {
    private int id;
    private String title;
    private String description;
    private double rating;
    private String category;
    private int userId;

    public Films() {
    }

    // Naujo įrašo kūrimui

    public Films(String title, String description, double rating, String category, int userId) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.category = category;
        this.userId = userId;
    }

    // įrašų iš duomenų bazės redagavimui ir paieškai

    public Films(int id, String title, String description, double rating, String category, int userId) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.category = category;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Films{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", category='" + category + '\'' +
                ", userId=" + userId +
                '}';
    }
}
