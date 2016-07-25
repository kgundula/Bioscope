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
 * Created by kgundula on 16/07/24.
 */
public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {

    private List<BioScope> bioScopes;
    Context context;

    public MoviesAdapter(Context context, List<BioScope> bioScopes) {
        this.context = context;
        this.bioScopes = bioScopes;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_card, parent, false);
        return new ViewHolder(view);
    }

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

    @Override
    public int getItemCount() {
        return bioScopes.size();
    }

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
