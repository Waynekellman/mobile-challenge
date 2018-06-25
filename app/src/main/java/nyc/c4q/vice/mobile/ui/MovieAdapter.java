package nyc.c4q.vice.mobile.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import java.util.Collections;
import java.util.List;
import nyc.c4q.vice.mobile.R;
import nyc.c4q.vice.mobile.model.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieViewHolder> {
  List<Movie> movieList = Collections.emptyList();

  void setData(List<Movie> nowPlaying) {
    this.movieList = nowPlaying;
    notifyDataSetChanged();
  }

  @NonNull @Override
  public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    LayoutInflater inflater = LayoutInflater.from(parent.getContext());
    return new MovieViewHolder(inflater.inflate(R.layout.movie_list_item, parent, false));
  }

  @Override public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
    holder.bind(movieList.get(position));
  }

  @Override public int getItemCount() {
    return movieList.size();
  }
}