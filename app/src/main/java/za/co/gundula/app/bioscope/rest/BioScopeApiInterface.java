package za.co.gundula.app.bioscope.rest;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import za.co.gundula.app.bioscope.model.BioScopeMovieResponse;
import za.co.gundula.app.bioscope.model.BioScopeReviewResponse;
import za.co.gundula.app.bioscope.model.BioScopesResponse;

/**
 * Defines the API endpoints for the BioScope app.
 */
public interface BioScopeApiInterface {

    /**
     * Gets the top rated movies.
     *
     * @param apiKey The API key to use for the request.
     * @return A Call object that can be used to make the request.
     */
    @GET("movie/top_rated")
    Call<BioScopesResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    /**
     * Gets the most popular movies.
     *
     * @param apiKey The API key to use for the request.
     * @return A Call object that can be used to make the request.
     */
    @GET("movie/popular")
    Call<BioScopesResponse> getMostPopularMovies(@Query("api_key") String apiKey);

    /**
     * Gets the reviews for a movie.
     *
     * @param id     The ID of the movie to get reviews for.
     * @param apiKey The API key to use for the request.
     * @return A Call object that can be used to make the request.
     */
    @GET("movie/{id}/reviews")
    Call<BioScopeReviewResponse> getMovieReviews(@Path("id") int id, @Query("api_key") String apiKey);

    /**
     * Gets the trailers for a movie.
     *
     * @param id     The ID of the movie to get trailers for.
     * @param apiKey The API key to use for the request.
     * @return A Call object that can be used to make the request.
     */
    @GET("movie/{id}/trailers")
    Call<BioScopeMovieResponse> getMovieTrailers(@Path("id") int id, @Query("api_key") String apiKey);

}
