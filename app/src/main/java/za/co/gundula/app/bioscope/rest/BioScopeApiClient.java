package za.co.gundula.app.bioscope.rest;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import za.co.gundula.app.bioscope.utils.Constants;

/**
 * Created by kgundula on 16/07/24.
 */
public class BioScopeApiClient {

    public static final String BASE_URL = Constants.IMDB_BASE_URL + "/" + Constants.API_VER + "/";
    private static Retrofit retrofit = null;

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
