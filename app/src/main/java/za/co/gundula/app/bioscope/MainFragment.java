package za.co.gundula.app.bioscope;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.gundula.app.bioscope.adapter.MoviesAdapter;
import za.co.gundula.app.bioscope.data.BioScopeContract;
import za.co.gundula.app.bioscope.model.BioScope;
import za.co.gundula.app.bioscope.model.BioScopesResponse;
import za.co.gundula.app.bioscope.rest.BioScopeApiClient;
import za.co.gundula.app.bioscope.rest.BioScopeApiInterface;
import za.co.gundula.app.bioscope.utils.Constants;
import za.co.gundula.app.bioscope.utils.RecyclerItemClickListener;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MainFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MainFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private SharedPreferences mSharedPref;
    private SharedPreferences.Editor mSharedPrefEditor;

    @BindView(R.id.recycler_view_movies)
    RecyclerView recyclerView;
    @BindView(R.id.bioscope_swipe_to_refresh)
    SwipeRefreshLayout bioscopeSwipeToRefresh;
    @BindView(R.id.no_favorites)
    TextView no_favorites;

    MoviesAdapter moviesAdapter;
    GridLayoutManager gridLayoutManager;
    Context context;
    String API_KEY = "";
    List<BioScope> bioScopes = new ArrayList<BioScope>();
    BioScopeApiInterface apiService;

    Toolbar toolbar;

    private int mPosition = RecyclerView.NO_POSITION;

    private static final String[] MOVIE_PROJECTION = new String[]{BioScopeContract.MoviesEntry.COLUMN_NAME_ID,
            BioScopeContract.MoviesEntry.COLUMN_NAME_TITLE,
            BioScopeContract.MoviesEntry.COLUMN_NAME_ORIGINAL_TITLE,
            BioScopeContract.MoviesEntry.COLUMN_NAME_OVERVIEW,
            BioScopeContract.MoviesEntry.COLUMN_NAME_RELEASE_DATE,
            BioScopeContract.MoviesEntry.COLUMN_NAME_POSTER_PATH,
            BioScopeContract.MoviesEntry.COLUMN_NAME_VOTE_AVERAGE,
            BioScopeContract.MoviesEntry.COLUMN_NAME_POPULARITY,
            BioScopeContract.MoviesEntry.COLUMN_NAME_ORIGINAL_LANGUAGE
    };

    public static final int COLUMN_NAME_ID = 0;
    public static final int COLUMN_NAME_TITLE = 1;
    public static final int COLUMN_NAME_ORIGINAL_TITLE = 2;
    public static final int COLUMN_NAME_OVERVIEW = 3;
    public static final int COLUMN_NAME_RELEASE_DATE = 4;
    public static final int COLUMN_NAME_POSTER_PATH = 5;
    public static final int COLUMN_NAME_VOTE_AVERAGE = 6;
    public static final int COLUMN_NAME_POPULARITY = 7;
    public static final int COLUMN_NAME_ORIGINAL_LANGUAGE = 7;

    private static final int MOVIE_LOADER = 0;
    private boolean movie_loader_loaded;
    private boolean mTwoPane;

    public MainFragment() {
        setHasOptionsMenu(true);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        context = getContext();

        mSharedPref = PreferenceManager.getDefaultSharedPreferences(context);
        mSharedPrefEditor = mSharedPref.edit();

        API_KEY = Constants.API_KEY;

        movie_loader_loaded = false;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, view);
        final String sort_key = mSharedPref.getString(Constants.SORT_KEY, "");

        gridLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, float x, float y) {

                mTwoPane = ((MainActivity) getActivity()).isTablet();
                BioScope bioScope = bioScopes.get(position);

                if (mTwoPane) {

                    ((MainActivity) getActivity()).replaceFragment(bioScope);

                } else {
                    Intent intent = new Intent(context, BioScopeDetailsActivity.class);
                    intent.putExtra(BioScopeDetailsActivity.bioscope_object, bioScope);
                    intent.putExtra(BioScopeDetailsActivity.two_pane, mTwoPane);
                    startActivity(intent);
                }
            }
        }));

        apiService = BioScopeApiClient.getClient().create(BioScopeApiInterface.class);


        if (getResources().getString(R.string.favorites).equals(sort_key)) {
            getMovieListLoader();
        } else {
            getMovieList(sort_key);
        }

        bioscopeSwipeToRefresh.setColorSchemeResources(R.color.colorOrange, R.color.colorGreen, R.color.colorBlue);
        bioscopeSwipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (getResources().getString(R.string.favorites).equals(sort_key)) {
                            getMovieListLoader();
                        } else {
                            getMovieList(sort_key);
                        }
                    }
                }, 2500);
            }
        });

        return view;
    }

    public void getMovieList(String sort_key) {

        bioscopeSwipeToRefresh.setRefreshing(true);
        Call<BioScopesResponse> call;
        if (getResources().getString(R.string.most_popular).equals(sort_key)) {
            call = apiService.getMostPopularMovies(API_KEY);
        } else if (getResources().getString(R.string.top_rated).equals(sort_key)) {
            call = apiService.getTopRatedMovies(API_KEY);
        } else {
            call = apiService.getMostPopularMovies(API_KEY);
            /*
                I chose most popular as the default,
                On first run set the sub titile to most popular
             */
            sort_key = getResources().getString(R.string.most_popular);
            mSharedPrefEditor.putString(Constants.SORT_KEY, getResources().getString(R.string.most_popular)).apply();
        }
        call.enqueue(new Callback<BioScopesResponse>() {
            @Override
            public void onResponse(Call<BioScopesResponse> call, Response<BioScopesResponse> response) {
                bioscopeSwipeToRefresh.setRefreshing(false);
                bioScopes = response.body().getResults();
                moviesAdapter = new MoviesAdapter(context, bioScopes);
                moviesAdapter.notifyDataSetChanged();
                recyclerView.setAdapter(moviesAdapter);
            }

            @Override
            public void onFailure(Call<BioScopesResponse> call, Throwable t) {

            }
        });
        ((MainActivity) getActivity()).setSubTitle(sort_key);
        hideNoFavoritesText();
    }

    public void hideNoFavoritesText() {
        no_favorites.setVisibility(View.GONE);
        no_favorites.invalidate();
    }

    public void showNoFavoritesText() {
        no_favorites.setVisibility(View.VISIBLE);
        no_favorites.invalidate();
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

    public void getMovieListLoader() {
        bioscopeSwipeToRefresh.setRefreshing(true);
        String sort_key = mSharedPref.getString(Constants.SORT_KEY, "");
        ((MainActivity) getActivity()).setSubTitle(sort_key);

        if (!movie_loader_loaded) {
            getLoaderManager().initLoader(MOVIE_LOADER, null, this);
        } else {
            getLoaderManager().restartLoader(MOVIE_LOADER, null, this);
        }
        bioscopeSwipeToRefresh.setRefreshing(false);

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {

        String sortOrder = BioScopeContract.MoviesEntry.COLUMN_NAME_TITLE + " ASC";

        Uri movie_uri = BioScopeContract.MoviesEntry.CONTENT_URI;

        return new CursorLoader(
                getActivity(),
                movie_uri,
                MOVIE_PROJECTION,
                null,
                null,
                sortOrder
        );
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {

        ArrayList<Integer> genre_ids = new ArrayList<>();
        if (data != null && data.moveToFirst()) {

            bioScopes.clear();
            do {
                BioScope bioScope = new BioScope(data.getString(COLUMN_NAME_POSTER_PATH), false, data.getString(COLUMN_NAME_OVERVIEW), data.getString(COLUMN_NAME_RELEASE_DATE), genre_ids,
                        data.getInt(COLUMN_NAME_ID), data.getString(COLUMN_NAME_ORIGINAL_TITLE), data.getString(COLUMN_NAME_ORIGINAL_LANGUAGE),
                        data.getString(COLUMN_NAME_TITLE), "", data.getDouble(COLUMN_NAME_POPULARITY), 0, false, data.getDouble(COLUMN_NAME_VOTE_AVERAGE));
                bioScopes.add(bioScope);
            } while (data.moveToNext());
            moviesAdapter = new MoviesAdapter(context, bioScopes);
            moviesAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(moviesAdapter);
            hideNoFavoritesText();

        } else {

            bioScopes.clear();
            moviesAdapter = new MoviesAdapter(context, bioScopes);
            moviesAdapter.notifyDataSetChanged();
            recyclerView.setAdapter(moviesAdapter);

            showNoFavoritesText();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        String sort_key = "";
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            return true;
        } else if (id == R.id.action_most_popular) {
            mSharedPrefEditor.putString(Constants.SORT_KEY, getResources().getString(R.string.most_popular)).apply();
            sort_key = getResources().getString(R.string.most_popular);
            getMovieList(getResources().getString(R.string.most_popular));
        } else if (id == R.id.action_top_rated) {
            mSharedPrefEditor.putString(Constants.SORT_KEY, getResources().getString(R.string.top_rated)).apply();
            sort_key = getResources().getString(R.string.top_rated);
            getMovieList(getResources().getString(R.string.top_rated));
        } else if (id == R.id.action_favorites) {
            mSharedPrefEditor.putString(Constants.SORT_KEY, getResources().getString(R.string.favorites)).apply();
            sort_key = getResources().getString(R.string.favorites);
            getMovieListLoader();
        }

        // Update Movies Based on New Sort Order
        return super.onOptionsItemSelected(item);
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
