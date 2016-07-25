package za.co.gundula.app.bioscope.model;

import java.util.List;

/**
 * Created by kgundula on 16/07/24.
 */
public class BioScopesResponse {

    private int page;
    private List<BioScope> results;
    private int totalResults;
    private int totalPages;

    public BioScopesResponse() {
    }

    public BioScopesResponse(int page, List<BioScope> results, int totalResults, int totalPages) {
        this.page = page;
        this.results = results;
        this.totalResults = totalResults;
        this.totalPages = totalPages;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public List<BioScope> getResults() {
        return results;
    }

    public void setResults(List<BioScope> results) {
        this.results = results;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
