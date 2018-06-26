package nyc.c4q.vice.mobile.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.List;
import nyc.c4q.vice.mobile.R;
import nyc.c4q.vice.mobile.db.FavoritesDatabaseHelper;
import nyc.c4q.vice.mobile.model.Movie;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

public class FavoritesView extends FrameLayout {
  private RecyclerView favoritesRecyclerView;
  private TextView emptyView;

  private MovieAdapter favoritesAdapter;

  public FavoritesView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();

    favoritesRecyclerView = findViewById(R.id.favorites);
    emptyView = findViewById(R.id.empty);

    favoritesRecyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), HORIZONTAL, false));
    favoritesAdapter = new MovieAdapter(R.layout.full_movie_list_item);
    favoritesRecyclerView.setAdapter(favoritesAdapter);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();

    FavoritesDatabaseHelper databaseHelper = FavoritesDatabaseHelper.getInstance(getContext());
    List<Movie> favorites = databaseHelper.getFavorites();
    if (favorites.isEmpty()) {
      favoritesRecyclerView.setVisibility(GONE);
      emptyView.setVisibility(VISIBLE);
    } else {
      favoritesAdapter.setData(favorites);
    }
  }
}
