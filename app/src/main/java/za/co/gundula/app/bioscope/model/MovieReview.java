package za.co.gundula.app.bioscope.model;

/**
 * Represents a single movie review.
 *
 * This class contains information about a movie review, including the author, content, and a URL to the full review.
 */
public class MovieReview {

    private String id;
    private String author;
    private String content;
    private String url;

    /**
     * Default constructor.
     */
    public MovieReview() {
    }

    /**
     * Constructs a new MovieReview object.
     *
     * @param id      The unique ID of the review.
     * @param author  The author of the review.
     * @param content The content of the review.
     * @param url     The URL to the full review.
     */
    public MovieReview(String id, String author, String content, String url) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.url = url;
    }

    /**
     * Gets the unique ID of the review.
     * @return The unique ID of the review.
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique ID of the review.
     * @param id The new unique ID of the review.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the author of the review.
     * @return The author of the review.
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Sets the author of the review.
     * @param author The new author of the review.
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * Gets the content of the review.
     * @return The content of the review.
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the content of the review.
     * @param content The new content of the review.
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the URL to the full review.
     * @return The URL to the full review.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the URL to the full review.
     * @param url The new URL to the full review.
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
