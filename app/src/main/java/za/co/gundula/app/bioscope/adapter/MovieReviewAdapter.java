package za.co.gundula.app.bioscope.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.gundula.app.bioscope.R;
import za.co.gundula.app.bioscope.model.MovieReview;

/**
 * Created by kgundula on 16/08/13.
 */
public class MovieReviewAdapter extends RecyclerView.Adapter<MovieReviewAdapter.ViewHolder> {

    Context context;
    private List<MovieReview> movieRevies;

    public MovieReviewAdapter(Context context, List<MovieReview> movieRevies) {
        this.context = context;
        this.movieRevies = movieRevies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_review_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MovieReviewAdapter.ViewHolder holder, int position) {
        holder.review_author.setText(movieRevies.get(position).getAuthor());
        holder.review_content.setText(movieRevies.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return movieRevies == null ? 0 : movieRevies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.review_author)
        TextView review_author;
        @BindView(R.id.review_content)
        TextView review_content;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
