package za.co.gundula.app.bioscope;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.gundula.app.bioscope.model.BioScope;
import za.co.gundula.app.bioscope.utils.Constants;
import za.co.gundula.app.bioscope.utils.Utility;

public class BioScopeDetailsActivity extends AppCompatActivity {

    Context context;

    @BindView(R.id.movie_poster)
    ImageView movie_poster;
    @BindView(R.id.release_date)
    TextView release_date;
    @BindView(R.id.synopsis)
    TextView synopsis;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.ratings)
    TextView ratings;
    Toolbar toolbar;

    public static String bioscope_object = "bioscope_object";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bio_scope_details);
        context = getApplicationContext();
        ButterKnife.bind(this);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        Bundle bundle = getIntent().getExtras();
        final BioScope bioScope = bundle.getParcelable(bioscope_object);

        assert bioScope != null;
        toolbar.setTitle(bioScope.getTitle());
        setSupportActionBar(toolbar);
        //noinspection ConstantConditions
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String rating = String.valueOf(bioScope.getVote_average());
        ratings.setText(rating);
        synopsis.setText(bioScope.getOverview());

        String movie_release_date = getResources().getString(R.string.release_date) + bioScope.getRelease_date();
        release_date.setText(movie_release_date);
        ratingBar.setNumStars(5);
        ratingBar.setStepSize(2f);
        float ratingStars = Utility.calculateRating((float) bioScope.getVote_average());
        ratingBar.setRating(ratingStars);

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
    }
}
