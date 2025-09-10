package za.co.gundula.app.bioscope.model;

import java.util.List;

/**
 * Represents a response containing movie trailers.
 *
 * This class holds lists of movie trailers from different sources, such as YouTube and QuickTime.
 */
public class BioScopeMovieResponse {

    private int id;
    List<MovieTrailer> quicktime;
    List<MovieTrailer> youtube;

    /**
     * Default constructor.
     */
    public BioScopeMovieResponse() {
    }

    /**
     * Constructs a new BioScopeMovieResponse object.
     *
     * @param id        The ID of the movie.
     * @param quicktime A list of QuickTime movie trailers.
     * @param youtube   A list of YouTube movie trailers.
     */
    public BioScopeMovieResponse(int id, List<MovieTrailer> quicktime, List<MovieTrailer> youtube) {
        this.id = id;
        this.quicktime = quicktime;
        this.youtube = youtube;
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
     * Gets the list of QuickTime movie trailers.
     * @return The list of QuickTime movie trailers.
     */
    public List<MovieTrailer> getQuicktime() {
        return quicktime;
    }

    /**
     * Sets the list of QuickTime movie trailers.
     * @param quicktime The new list of QuickTime movie trailers.
     */
    public void setQuicktime(List<MovieTrailer> quicktime) {
        this.quicktime = quicktime;
    }

    /**
     * Gets the list of YouTube movie trailers.
     * @return The list of YouTube movie trailers.
     */
    public List<MovieTrailer> getYoutube() {
        return youtube;
    }

    /**
     * Sets the list of YouTube movie trailers.
     * @param youtube The new list of YouTube movie trailers.
     */
    public void setYoutube(List<MovieTrailer> youtube) {
        this.youtube = youtube;
    }
}
