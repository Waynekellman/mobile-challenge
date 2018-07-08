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
import com.jakewharton.rxbinding2.support.design.widget.RxBottomNavigationView;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.jakewharton.rxrelay2.PublishRelay;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import javax.inject.Inject;
import nyc.c4q.vice.mobile.R;
import nyc.c4q.vice.mobile.ViceApp;
import nyc.c4q.vice.mobile.presenters.MainPresenter;
import nyc.c4q.vice.mobile.viewmodels.MainViewEvent;
import nyc.c4q.vice.mobile.viewmodels.MainViewModel;
import nyc.c4q.vice.mobile.viewmodels.TabSelected;

public class MainView extends ConstraintLayout implements Consumer<MainViewModel>,
    ObservableSource<MainViewEvent> {
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.container) ViewGroup container;
  @BindView(R.id.bottom_navigation) BottomNavigationView bottomNavigationView;

  private View homeView;
  private View favoritesView;

  private BehaviorRelay<MainViewModel> viewModels = BehaviorRelay.create();
  private PublishRelay<MainViewEvent> viewEvents = PublishRelay.create();
  private CompositeDisposable disposables = new CompositeDisposable();

  @Inject MainPresenter presenter;

  public MainView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    ButterKnife.bind(this);
    ((ViceApp) getContext().getApplicationContext()).component().inject(this);

    LayoutInflater layoutInflater = LayoutInflater.from(getContext());

    homeView = layoutInflater.inflate(R.layout.view_home, null);
    favoritesView = layoutInflater.inflate(R.layout.view_favorites, null);

    // set the initial view and toolbar text
    container.addView(homeView);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();

    // The wrap() operator wraps an ObservableSource into an Observable if not already an Observable.
    // As a result,
    //   presenter -> view model -> view
    //   view -> view event -> presenter
    disposables.add(Observable.wrap(this).subscribe(presenter));
    disposables.add(Observable.wrap(presenter).subscribe(this));

    // this stream populates the view given the latest viewmodel from the presenter
    disposables.add(
        viewModels.subscribe(viewModel -> {
          toolbar.setTitle(viewModel.toolbarTitle);
          container.removeAllViews();

          if (viewModel.selectedTabLayoutId == R.id.action_home) {
            container.addView(homeView);
          } else if (viewModel.selectedTabLayoutId == R.id.action_favorites) {
            container.addView(favoritesView);
          } else {
            throw new IllegalStateException(
                "Unknown menu item id: " +
                    getResources().getResourceName(viewModel.selectedTabLayoutId)
            );
          }
        })
    );

    // this stream emits a new viewevent to the presenter upon item click
    disposables.add(
        RxBottomNavigationView.itemSelections(bottomNavigationView)
            .subscribe(menuItem -> viewEvents.accept(new TabSelected(menuItem.getItemId())))
    );
  }

  @Override protected void onDetachedFromWindow() {
    super.onDetachedFromWindow();
    disposables.dispose();
  }

  // any observer (i.e., a presenter) that subscribes to this view is actually
  // subscribing to the viewevent stream
  @Override public void subscribe(Observer<? super MainViewEvent> observer) {
    viewEvents.subscribe(observer);
  }

  // any emission to this view is actually emitting to the viewmodel stream
  @Override public void accept(MainViewModel viewModel) {
    viewModels.accept(viewModel);
  }
}