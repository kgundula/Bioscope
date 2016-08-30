package za.co.gundula.app.bioscope.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import za.co.gundula.app.bioscope.R;
import za.co.gundula.app.bioscope.model.MovieTrailer;
import za.co.gundula.app.bioscope.utils.Constants;

/**
 * Created by kgundula on 16/08/13.
 */
public class MovieTrailerAdapter extends RecyclerView.Adapter<MovieTrailerAdapter.ViewHolder> {

    Context context;
    private List<MovieTrailer> movieTrailers;

    public MovieTrailerAdapter(Context context, List<MovieTrailer> movieTrailers) {
        this.context = context;
        this.movieTrailers = movieTrailers;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.movie_trailer_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        holder.trailer_name.setText(movieTrailers.get(position).getName());
        holder.movie_trailer.setTag(position);
        holder.movie_trailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int pos = (Integer) view.getTag();
                MovieTrailer movieTrailer = movieTrailers.get(pos);
                if (!"".equals(movieTrailer.getSource())) {
                    String trailer_url = Constants.YOUTUBE_VIDEO_BASE_URL + movieTrailer.getSource();
                    context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(trailer_url)));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return movieTrailers == null ? 0 : movieTrailers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.trailer_name)
        TextView trailer_name;
        @BindView(R.id.movie_trailer)
        ImageView movie_trailer;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
