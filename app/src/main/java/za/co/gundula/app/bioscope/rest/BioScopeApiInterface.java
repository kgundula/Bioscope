package za.co.gundula.app.bioscope.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import za.co.gundula.app.bioscope.model.BioScopesResponse;

/**
 * Created by kgundula on 16/07/24.
 */
public interface BioScopeApiInterface {

    @GET("movie/top_rated")
    Call<BioScopesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<BioScopesResponse> getMostPopularMovies(@Query("api_key") String apiKey);

}
