package za.co.gundula.app.bioscope.model;

import java.util.List;

/**
 * Created by kgundula on 16/07/28.
 */
public class BioScopeMovieResponse {

    private int id;
    List<MovieTrailer> quicktime;
    List<MovieTrailer> youtube;

    public BioScopeMovieResponse() {
    }

    public BioScopeMovieResponse(int id, List<MovieTrailer> quicktime, List<MovieTrailer> youtube) {
        this.id = id;
        this.quicktime = quicktime;
        this.youtube = youtube;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MovieTrailer> getQuicktime() {
        return quicktime;
    }

    public void setQuicktime(List<MovieTrailer> quicktime) {
        this.quicktime = quicktime;
    }

    public List<MovieTrailer> getYoutube() {
        return youtube;
    }

    public void setYoutube(List<MovieTrailer> youtube) {
        this.youtube = youtube;
    }
}
