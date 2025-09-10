package za.co.gundula.app.bioscope.model;

import java.util.List;

/**
 * Represents a response containing a list of movies.
 *
 * This class holds a list of movies, as well as pagination information.
 */
public class BioScopesResponse {

    private int page;
    private List<BioScope> results;
    private int totalResults;
    private int totalPages;

    /**
     * Default constructor.
     */
    public BioScopesResponse() {
    }

    /**
     * Constructs a new BioScopesResponse object.
     *
     * @param page         The current page of results.
     * @param results      A list of movies.
     * @param totalResults The total number of results.
     * @param totalPages   The total number of pages of results.
     */
    public BioScopesResponse(int page, List<BioScope> results, int totalResults, int totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
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
     * Gets the list of movies.
     * @return The list of movies.
     */
    public List<BioScope> getResults() {
        return results;
    }

    /**
     * Sets the list of movies.
     * @param results The new list of movies.
     */
    public void setResults(List<BioScope> results) {
        this.results = results;
    }

    /**
     * Gets the total number of results.
     * @return The total number of results.
     */
    public int getTotalResults() {
        return totalResults;
    }

    /**
     * Sets the total number of results.
     * @param totalResults The new total number of results.
     */
    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    /**
     * Gets the total number of pages of results.
     * @return The total number of pages of results.
     */
    public int getTotalPages() {
        return totalPages;
    }

    /**
     * Sets the total number of pages of results.
     * @param totalPages The new total number of pages of results.
     */
    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
