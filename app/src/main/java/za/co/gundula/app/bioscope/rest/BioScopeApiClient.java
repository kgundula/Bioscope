package za.co.gundula.app.bioscope.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import za.co.gundula.app.bioscope.utils.Constants;

/**
 * Provides a singleton Retrofit client for making API calls.
 */
public class BioScopeApiClient {

    public static final String BASE_URL = Constants.IMDB_BASE_URL + "/" + Constants.API_VER + "/";
    private static Retrofit retrofit = null;

    /**
     * Gets the Retrofit client.
     *
     * @return The Retrofit client.
     */
    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
