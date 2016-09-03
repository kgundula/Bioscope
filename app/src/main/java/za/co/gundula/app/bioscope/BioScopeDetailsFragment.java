package za.co.gundula.app.bioscope;

import android.content.ContentValues;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Response;
import za.co.gundula.app.bioscope.adapter.MovieReviewAdapter;
import za.co.gundula.app.bioscope.adapter.MovieTrailerAdapter;
import za.co.gundula.app.bioscope.data.BioScopeContract;
import za.co.gundula.app.bioscope.model.BioScope;
import za.co.gundula.app.bioscope.model.BioScopeMovieResponse;
import za.co.gundula.app.bioscope.model.BioScopeReviewResponse;
import za.co.gundula.app.bioscope.model.MovieReview;
import za.co.gundula.app.bioscope.model.MovieTrailer;
import za.co.gundula.app.bioscope.rest.BioScopeApiClient;
import za.co.gundula.app.bioscope.rest.BioScopeApiInterface;
import za.co.gundula.app.bioscope.utils.Constants;
import za.co.gundula.app.bioscope.utils.RecylerViewDividerItemDecoration;
import za.co.gundula.app.bioscope.utils.Utility;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BioScopeDetailsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BioScopeDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BioScopeDetailsFragment extends Fragment {

    @BindView(R.id.release_date)
    TextView release_date;
    @BindView(R.id.synopsis)
    TextView synopsis;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    @BindView(R.id.ratings)
    TextView ratings;
    @BindView(R.id.fragment_movie_poster)
    ImageView fragment_movie_poster;
    @BindView(R.id.recycler_view_movies_review)
    RecyclerView recycler_view_movies_review;
    @BindView(R.id.recycler_view_movies_trailer)
    RecyclerView recycler_view_movies_trailer;
    @BindView(R.id.button_add_favorites)
    ImageView button_add_favorites;

    LinearLayoutManager llmReviews, llmTrailers;

    BioScope mBioScope;
    boolean mTwoPane;
    String API_KEY = "";

    BioScopeApiInterface apiService;
    List<MovieReview> movieReviews = new ArrayList<MovieReview>();
    List<MovieTrailer> movieTrailers = new ArrayList<MovieTrailer>();

    MovieReviewAdapter movieReviewsAdapter;
    MovieTrailerAdapter movieTrailerAdapter;

    Context context;

    private OnFragmentInteractionListener mListener;

    public BioScopeDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    public static BioScopeDetailsFragment newInstance(Parcelable param1, boolean param2) {
        BioScopeDetailsFragment fragment = new BioScopeDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(BioScopeDetailsActivity.bioscope_object, param1);
        args.putBoolean(BioScopeDetailsActivity.two_pane, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mBioScope = getArguments().getParcelable(BioScopeDetailsActivity.bioscope_object);
            mTwoPane = getArguments().getBoolean(BioScopeDetailsActivity.two_pane);
        }
        apiService = BioScopeApiClient.getClient().create(BioScopeApiInterface.class);
        context = getContext();
        API_KEY = Constants.API_KEY;
        llmReviews = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        llmTrailers = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details_bioscope, container, false);
        ButterKnife.bind(this, view);

        //assert mBioScope != null;
        if (mBioScope != null) {
            String rating = String.valueOf(mBioScope.getVote_average());
            ratings.setText(rating);
            synopsis.setText(mBioScope.getOverview());
            String movie_release_date = getResources().getString(R.string.release_date) + mBioScope.getRelease_date();
            release_date.setText(movie_release_date);
            ratingBar.setNumStars(5);
            ratingBar.setStepSize(2f);
            float ratingStars = Utility.calculateRating((float) mBioScope.getVote_average());
            ratingBar.setRating(ratingStars);
            ratingBar.setVisibility(View.VISIBLE);
            ratingBar.invalidate();

            if (mTwoPane) {
                fragment_movie_poster.setVisibility(View.VISIBLE);
                final String url = Constants.IMDB_IMG_BASE_URL + "/" + Constants.w185 + "/" + mBioScope.getPoster_path();
                Picasso.with(context).load(url).networkPolicy(NetworkPolicy.OFFLINE).into(fragment_movie_poster, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        //Try again online if cache failed
                        Picasso.with(context)
                                .load(url)
                                .into(fragment_movie_poster, new Callback() {
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

            recycler_view_movies_review.addItemDecoration(new RecylerViewDividerItemDecoration(context));
            recycler_view_movies_review.setHasFixedSize(true);

            recycler_view_movies_trailer.addItemDecoration(new RecylerViewDividerItemDecoration(context));
            recycler_view_movies_trailer.setHasFixedSize(true);

            getMovieReviews(mBioScope.getId());
            getMovieTrailer(mBioScope.getId());

            button_add_favorites.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    addMovieToFavorites();
                    showSnackBar("Added to favorites");
                }
            });
            button_add_favorites.setVisibility(View.VISIBLE);

        }
        return view;
    }

    public void addMovieToFavorites() {

        int id = mBioScope.getId();
        String poster_path = mBioScope.getPoster_path();
        String overview = mBioScope.getOverview();
        String release_date = mBioScope.getRelease_date();
        String title = mBioScope.getTitle();
        double vote_average = mBioScope.getVote_average();
        double popularity = mBioScope.getPopularity();
        String original_title = mBioScope.getOriginal_title();
        String original_language = mBioScope.getOriginal_language();

        ContentValues movieValues = new ContentValues();
        movieValues.put(BioScopeContract.MoviesEntry.COLUMN_NAME_ID, id);
        movieValues.put(BioScopeContract.MoviesEntry.COLUMN_NAME_POSTER_PATH, poster_path);
        movieValues.put(BioScopeContract.MoviesEntry.COLUMN_NAME_OVERVIEW, overview);
        movieValues.put(BioScopeContract.MoviesEntry.COLUMN_NAME_RELEASE_DATE, release_date);
        movieValues.put(BioScopeContract.MoviesEntry.COLUMN_NAME_TITLE, title);
        movieValues.put(BioScopeContract.MoviesEntry.COLUMN_NAME_VOTE_AVERAGE, vote_average);
        movieValues.put(BioScopeContract.MoviesEntry.COLUMN_NAME_POPULARITY, popularity);
        movieValues.put(BioScopeContract.MoviesEntry.COLUMN_NAME_ORIGINAL_TITLE, original_title);
        movieValues.put(BioScopeContract.MoviesEntry.COLUMN_NAME_ORIGINAL_LANGUAGE, original_language);

        try {
            getContext().getContentResolver().insert(BioScopeContract.MoviesEntry.CONTENT_URI, movieValues);
            getContext().getContentResolver().notifyChange(BioScopeContract.MoviesEntry.CONTENT_URI, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (!mTwoPane) {
            fragment_movie_poster.setVisibility(View.GONE);
            fragment_movie_poster.invalidate();
        } else {
            fragment_movie_poster.setVisibility(View.VISIBLE);
            fragment_movie_poster.invalidate();
        }
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void showSnackBar(String msg) {

    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

    public void getMovieTrailer(int movie_id) {
        Call<BioScopeMovieResponse> call = apiService.getMovieTrailers(movie_id, API_KEY);
        call.enqueue(new retrofit2.Callback<BioScopeMovieResponse>() {
            @Override
            public void onResponse(Call<BioScopeMovieResponse> call, Response<BioScopeMovieResponse> response) {
                movieTrailers = response.body().getYoutube();
                movieTrailerAdapter = new MovieTrailerAdapter(context, movieTrailers);
                movieTrailerAdapter.notifyDataSetChanged();
                recycler_view_movies_trailer.setAdapter(movieTrailerAdapter);
            }

            @Override
            public void onFailure(Call<BioScopeMovieResponse> call, Throwable t) {

            }
        });
    }

    public void getMovieReviews(int movie_id) {

        Call<BioScopeReviewResponse> call = apiService.getMovieReviews(movie_id, API_KEY);
        call.enqueue(new retrofit2.Callback<BioScopeReviewResponse>() {
            @Override
            public void onResponse(Call<BioScopeReviewResponse> call, Response<BioScopeReviewResponse> response) {
                movieReviews = response.body().getResults();
                movieReviewsAdapter = new MovieReviewAdapter(context, movieReviews);
                movieReviewsAdapter.notifyDataSetChanged();
                recycler_view_movies_review.setAdapter(movieReviewsAdapter);
            }

            @Override
            public void onFailure(Call<BioScopeReviewResponse> call, Throwable t) {

            }
        });
    }


}
