package nyc.c4q.vice.mobile.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.TextView;
import nyc.c4q.vice.mobile.R;

import static android.support.v7.widget.LinearLayoutManager.HORIZONTAL;

public class FavoritesView extends FrameLayout {
  private RecyclerView favoritesRecyclerView;
  private TextView emptyView;

  public FavoritesView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();

    favoritesRecyclerView = findViewById(R.id.favorites);
    emptyView = findViewById(R.id.empty);

    favoritesRecyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), HORIZONTAL, false));
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();

    favoritesRecyclerView.setVisibility(GONE);
    emptyView.setVisibility(VISIBLE);
  }
}
