package nyc.c4q.vice.mobile.ui;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import butterknife.ButterKnife;
import nyc.c4q.vice.mobile.R;

public class MainView extends ConstraintLayout {
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.container) ViewGroup container;
  @BindView(R.id.bottom_navigation) BottomNavigationView bottomNavigationView;

  private View homeView;
  private View favoritesView;

  public MainView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this);

    LayoutInflater layoutInflater = LayoutInflater.from(getContext());

    homeView = layoutInflater.inflate(R.layout.view_home, null);
    favoritesView = layoutInflater.inflate(R.layout.view_favorites, null);

    // set the initial view and toolbar text
    container.addView(homeView);
    toolbar.setTitle(R.string.home_title);

    // logic to switch views upon tab click
    bottomNavigationView.setOnNavigationItemSelectedListener(menuItem -> {
      // based on tab click, decide whether to show home or favorites view
      switch (menuItem.getItemId()) {
        case R.id.action_home:
          container.removeAllViews();
          container.addView(homeView);
          toolbar.setTitle(R.string.home_title);
          break;
        case R.id.action_favorites:
          container.removeAllViews();
          container.addView(favoritesView);
          toolbar.setTitle(R.string.favorites_title);
          break;
        default:
          throw new IllegalStateException(
              "Unknown menu item id: " + getResources().getResourceName(menuItem.getItemId())
          );
      }

      return true;
    });
  }
}