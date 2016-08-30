package za.co.gundula.app.bioscope.model;

import java.util.List;

/**
 * Created by kgundula on 16/07/28.
 */
public class BioScopeReviewResponse {

    private int id;
    private int page;
    private List<MovieReview> results;
    private int total_pages;
    private int total_results;

    public BioScopeReviewResponse() {
    }

    public BioScopeReviewResponse(int id, int page, List<MovieReview> results, int total_pages, int total_results) {
        this.id = id;
        this.page = page;
        this.results = results;
        this.total_pages = total_pages;
        this.total_results = total_results;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<MovieReview> getResults() {
        return results;
    }

    public void setResults(List<MovieReview> results) {
        this.results = results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }
}
