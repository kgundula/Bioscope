package za.co.gundula.app.bioscope.model;

import java.util.List;

/**
 * Represents a response containing movie reviews.
 *
 * This class holds a list of movie reviews, as well as pagination information.
 */
public class BioScopeReviewResponse {

    private int id;
    private int page;
    private List<MovieReview> results;
    private int total_pages;
    private int total_results;

    /**
     * Default constructor.
     */
    public BioScopeReviewResponse() {
    }

    /**
     * Constructs a new BioScopeReviewResponse object.
     *
     * @param id            The ID of the movie.
     * @param page          The current page of results.
     * @param results       A list of movie reviews.
     * @param total_pages   The total number of pages of results.
     * @param total_results The total number of results.
     */
    public BioScopeReviewResponse(int id, int page, List<MovieReview> results, int total_pages, int total_results) {
        this.id = id;
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    /**
     * Gets the ID of the movie.
     * @return The ID of the movie.
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the movie.
     * @param id The new ID of the movie.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the current page of results.
     * @return The current page of results.
     */
    public int getPage() {
        return page;
    }

    /**
     * Sets the current page of results.
     * @param page The new current page of results.
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * Gets the list of movie reviews.
     * @return The list of movie reviews.
     */
    public List<MovieReview> getResults() {
        return results;
    }

    /**
     * Sets the list of movie reviews.
     * @param results The new list of movie reviews.
     */
    public void setResults(List<MovieReview> results) {
        this.results = results;
    }

    /**
     * Gets the total number of pages of results.
     * @return The total number of pages of results.
     */
    public int getTotal_pages() {
        return total_pages;
    }

    /**
     * Sets the total number of pages of results.
     * @param total_pages The new total number of pages of results.
     */
    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    /**
     * Gets the total number of results.
     * @return The total number of results.
     */
    public int getTotal_results() {
        return total_results;
    }

    /**
     * Sets the total number of results.
     * @param total_results The new total number of results.
     */
    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
