package nyc.c4q.vice.mobile.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import nyc.c4q.vice.mobile.R;

public class HomeView extends LinearLayout {
  private RecyclerView nowPlayingRecyclerView;
  private RecyclerView mostPopularRecyclerView;

  public HomeView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();

    nowPlayingRecyclerView = findViewById(R.id.now_playing);
    mostPopularRecyclerView = findViewById(R.id.most_popular);

    nowPlayingRecyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), HORIZONTAL, false));
    mostPopularRecyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), HORIZONTAL, false));
  }
}