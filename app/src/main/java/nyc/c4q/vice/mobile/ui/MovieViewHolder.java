package nyc.c4q.vice.mobile.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import nyc.c4q.vice.mobile.R;
import nyc.c4q.vice.mobile.model.Movie;

public class MovieViewHolder extends RecyclerView.ViewHolder {
  private static final String MOVIE_IMAGE_URL_PREFIX = "https://image.tmdb.org/t/p/w342/";

  ImageView image;
  TextView title;

  public MovieViewHolder(View view) {
    super(view);
    image = view.findViewById(R.id.movie_image);
    title = view.findViewById(R.id.movie_title);
  }

  public void bind(Movie movie) {
    String moviePosterUrl = MOVIE_IMAGE_URL_PREFIX + movie.poster_path;
    Picasso.get().load(moviePosterUrl).into(image);
    title.setText(movie.title);
  }
}