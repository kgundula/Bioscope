package za.co.gundula.app.bioscope;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.gundula.app.bioscope.model.BioScope;
import za.co.gundula.app.bioscope.utils.Constants;

/**
 * An activity that displays the details of a movie.
 */
public class BioScopeDetailsActivity extends AppCompatActivity implements BioScopeDetailsFragment.OnFragmentInteractionListener {

    Context context;
    Toolbar toolbar;

    public static String bioscope_object = "bioscope_object";
    public static String two_pane = "two_pane";

    @BindView(R.id.movie_poster)
    ImageView movie_poster;

    /**
     * Called when the activity is first created.
     *
     * @param savedInstanceState If the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle).
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_scope_details);
        context = getApplicationContext();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        final BioScope bioScope = bundle.getParcelable(bioscope_object);
        final boolean mTwoPane = bundle.getBoolean(two_pane);

        if (!mTwoPane) {
            toolbar.setTitle(bioScope.getTitle());
            setSupportActionBar(toolbar);
            //noinspection ConstantConditions
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

            final String url = Constants.IMDB_IMG_BASE_URL + "/" + Constants.w185 + "/" + bioScope.getPoster_path();
            Picasso.with(context).load(url).networkPolicy(NetworkPolicy.OFFLINE).into(movie_poster, new Callback() {
                @Override
                public void onSuccess() {

                }

                @Override
                public void onError() {
                    //Try again online if cache failed
                    Picasso.with(context)
                            .load(url)
                            .into(movie_poster, new Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError() {
                                    Log.i("Picasso", "Could not fetch image");
                                }
                            });
                }
            });

        } else {

        }


        if (savedInstanceState == null) {

            Bundle arguments = new Bundle();
            arguments.putParcelable(bioscope_object, bioScope);
            arguments.putBoolean(two_pane, mTwoPane);

            BioScopeDetailsFragment fragment = new BioScopeDetailsFragment();
            fragment.setArguments(arguments);
            fragment.setRetainInstance(true);

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.view_bioscope_details_container, fragment)
                    .commit();
        }


    }

    /**
     * Handles interaction with the contained BioScopeDetailsFragment.
     * This method is a required implementation of the OnFragmentInteractionListener interface,
     * but is not currently used in this application.
     *
     * @param uri The Uri to describe the interaction.
     */
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
