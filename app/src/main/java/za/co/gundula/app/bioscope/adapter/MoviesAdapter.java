package za.co.gundula.app.bioscope.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.gundula.app.bioscope.R;
import za.co.gundula.app.bioscope.model.BioScope;
import za.co.gundula.app.bioscope.utils.Constants;

/**
 * Adapter for displaying movies in a RecyclerView.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<BioScope> bioScopes;
    Context context;

    /**
     * Constructs a new MoviesAdapter.
     *
     * @param context   The context to use.
     * @param bioScopes A list of movies to display.
     */
    public MoviesAdapter(Context context, List<BioScope> bioScopes) {
        this.context = context;
        this.bioScopes = bioScopes;

    }

    /**
     * Called when a new ViewHolder is created.
     *
     * @param parent   The parent view.
     * @param viewType The type of the view.
     * @return A new ViewHolder.
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_card, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Called when a ViewHolder is bound to data.
     *
     * @param holder   The ViewHolder to bind.
     * @param position The position of the data in the list.
     */
    @Override
    public void onBindViewHolder(final MoviesAdapter.ViewHolder holder, int position) {
        final String url = Constants.IMDB_IMG_BASE_URL + "/" + Constants.w185 + "/" + bioScopes.get(position).getPoster_path();
        Picasso.with(context).load(url).networkPolicy(NetworkPolicy.OFFLINE).into(holder.movie_poster, new Callback() {
            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {
                //Try again online if cache failed
                Picasso.with(context)
                        .load(url)
                        .into(holder.movie_poster, new Callback() {
                            @Override
                            public void onSuccess() {

                            }

                            @Override
                            public void onError() {
                                Log.v("Picasso", "Could not fetch image");
                            }
                        });
            }
        });
    }

    /**
     * Gets the number of items in the list.
     *
     * @return The number of items in the list.
     */
    @Override
    public int getItemCount() {
        return bioScopes == null ? 0 : bioScopes.size();
    }

    /**
     * ViewHolder for a movie.
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_poster)
        ImageView movie_poster;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }


}
