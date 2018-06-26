package nyc.c4q.vice.mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.squareup.picasso.Picasso;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import nyc.c4q.vice.mobile.BuildConfig;
import nyc.c4q.vice.mobile.R;
import nyc.c4q.vice.mobile.api.MovieService;
import nyc.c4q.vice.mobile.db.FavoritesDatabaseHelper;
import nyc.c4q.vice.mobile.model.Movie;
import nyc.c4q.vice.mobile.model.Review;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailsActivity extends AppCompatActivity {
  private static final String MOVIE_BACKDROP_URL_PREFIX = "https://image.tmdb.org/t/p/w1280/";

  @BindView(R.id.image) ImageView imageView;
  @BindView(R.id.title) TextView titleView;
  @BindView(R.id.release_date) TextView releaseDateView;
  @BindView(R.id.rating) TextView ratingView;
  @BindView(R.id.overview) TextView overviewView;
  @BindView(R.id.reviews) ViewGroup reviews;
  @BindView(R.id.fab) FloatingActionButton fab;

  private FavoritesDatabaseHelper databaseHelper;
  private MovieService movieService;

  @Override protected void onCreate(@Nullable Bundle bundle) {
    super.onCreate(bundle);
    setContentView(R.layout.activity_details);
    ButterKnife.bind(this);

    databaseHelper = FavoritesDatabaseHelper.getInstance(this);

    Intent intent = getIntent();
    final int movieId = intent.getIntExtra("movie_id", 0);
    final String posterPath = intent.getStringExtra("poster_path");
    final String title = intent.getStringExtra("title");

    boolean isFavorite = databaseHelper.isFavorite(movieId);
    fab.setImageResource(isFavorite ? R.drawable.ic_done : R.drawable.ic_save);

    fab.setOnClickListener(v -> {
      boolean isFavorite1 = databaseHelper.isFavorite(movieId);
      if (isFavorite1) {
        databaseHelper.deleteFavorite(movieId);
        fab.setImageResource(R.drawable.ic_save);
      } else {
        databaseHelper.addFavorite(Movie.from(movieId, posterPath, title));
        fab.setImageResource(R.drawable.ic_done);
      }
    });

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
        .build();
    movieService = retrofit.create(MovieService.class);
  }

  @Override protected void onResume() {
    super.onResume();

    Intent intent = getIntent();
    int movieId = intent.getIntExtra("movie_id", 0);

    movieService.getMovieDetails(movieId, BuildConfig.MOVIE_DATABASE_API_KEY)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            details -> {
              String backdropPath = MOVIE_BACKDROP_URL_PREFIX + details.backdrop_path;
              Picasso.get().load(backdropPath).into(imageView);
              titleView.setText(details.title);
              releaseDateView.setText(details.release_date);
              ratingView.setText(String.valueOf(details.vote_average));
              overviewView.setText(details.overview);
            },
            t -> Log.e("C4Q", "Error obtaining movie details", t)
        );

    movieService.getReviews(movieId, BuildConfig.MOVIE_DATABASE_API_KEY)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            reviewResponse -> {
              for (Review review : reviewResponse.results) {
                TextView reviewView = new TextView(this);
                reviewView.setText(review.content);
                reviews.addView(reviewView);
              }
            },
            t -> Log.e("C4Q", "Error obtaining movie reviews", t)
        );
  }
}