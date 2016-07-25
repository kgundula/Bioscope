package za.co.gundula.app.bioscope;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import za.co.gundula.app.bioscope.adapter.MoviesAdapter;
import za.co.gundula.app.bioscope.model.BioScope;
import za.co.gundula.app.bioscope.model.BioScopesResponse;
import za.co.gundula.app.bioscope.rest.BioScopeApiClient;
import za.co.gundula.app.bioscope.rest.BioScopeApiInterface;
import za.co.gundula.app.bioscope.utils.Constants;
import za.co.gundula.app.bioscope.utils.RecyclerItemClickListener;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences mSharedPref;
    private SharedPreferences.Editor mSharedPrefEditor;

    @BindView(R.id.recycler_view_movies)
    RecyclerView recyclerView;
    @BindView(R.id.bioscope_swipe_to_refresh)
    SwipeRefreshLayout bioscopeSwipeToRefresh;

    MoviesAdapter moviesAdapter;
    GridLayoutManager gridLayoutManager;
    Context context;
    String API_KEY = "";
    List<BioScope> bioScopes = new ArrayList<BioScope>();
    BioScopeApiInterface apiService;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSharedPref = PreferenceManager.getDefaultSharedPreferences(this);
        mSharedPrefEditor = mSharedPref.edit();

        context = getApplicationContext();
        API_KEY = getResources().getString(R.string.movie_api_key);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);


        gridLayoutManager = new GridLayoutManager(context, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnItemTouchListener(new RecyclerItemClickListener(context, new RecyclerItemClickListener.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position, float x, float y) {
                BioScope bioScope = bioScopes.get(position);
                Intent intent = new Intent(context, BioScopeDetailsActivity.class);
                intent.putExtra(BioScopeDetailsActivity.bioscope_object, bioScope);
                startActivity(intent);
            }
        }));

        apiService = BioScopeApiClient.getClient().create(BioScopeApiInterface.class);
        getMovieList();

        bioscopeSwipeToRefresh.setColorSchemeResources(R.color.colorOrange, R.color.colorGreen, R.color.colorBlue);
        bioscopeSwipeToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getMovieList();
                    }
                }, 2500);
            }
        });


    }

    public void getMovieList() {

        bioscopeSwipeToRefresh.setRefreshing(true);

        String sort_key = mSharedPref.getString(Constants.SORT_KEY, "");

        Call<BioScopesResponse> call;
        if (getResources().getString(R.string.most_popular).equals(sort_key)) {
            call = apiService.getMostPopularMovies(API_KEY);
        } else if (getResources().getString(R.string.top_rated).equals(sort_key)) {
            call = apiService.getTopRatedMovies(API_KEY);
        } else {
            call = apiService.getMostPopularMovies(API_KEY); // I chose most popular as the default
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
    }

    @Override
    public void onResume() {
        super.onResume();
        String sort_key = mSharedPref.getString(Constants.SORT_KEY, "");
        if (!"".equals(sort_key))
            toolbar.setSubtitle(sort_key);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_filter) {
            return true;
        } else if (id == R.id.action_most_popular) {
            mSharedPrefEditor.putString(Constants.SORT_KEY, getResources().getString(R.string.most_popular)).apply();
            toolbar.setSubtitle(getResources().getString(R.string.most_popular));
        } else if (id == R.id.action_top_rated) {
            mSharedPrefEditor.putString(Constants.SORT_KEY, getResources().getString(R.string.top_rated)).apply();
            toolbar.setSubtitle(getResources().getString(R.string.top_rated));
        }
        // Update Movies Based on New Sort Order
        getMovieList();
        return super.onOptionsItemSelected(item);
    }

}
