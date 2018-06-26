package nyc.c4q.vice.mobile.ui;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.Toast;
import nyc.c4q.vice.mobile.R;
import nyc.c4q.vice.mobile.api.MovieService;
import nyc.c4q.vice.mobile.model.MovieResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static nyc.c4q.vice.mobile.api.MovieService.API_KEY;

public class HomeView extends LinearLayout {
  private RecyclerView nowPlayingRecyclerView;
  private RecyclerView mostPopularRecyclerView;

  private MovieAdapter nowPlayingAdapter;
  private MovieAdapter mostPopularAdapter;
  private MovieService movieService;

  public HomeView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
  }

  @Override protected void onFinishInflate() {
    super.onFinishInflate();

    nowPlayingRecyclerView = findViewById(R.id.now_playing);
    mostPopularRecyclerView = findViewById(R.id.most_popular);

    nowPlayingRecyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), HORIZONTAL, false));
    nowPlayingAdapter = new MovieAdapter();
    nowPlayingRecyclerView.setAdapter(nowPlayingAdapter);

    mostPopularRecyclerView.setLayoutManager(
        new LinearLayoutManager(getContext(), HORIZONTAL, false));
    mostPopularAdapter = new MovieAdapter();
    mostPopularRecyclerView.setAdapter(mostPopularAdapter);
  }

  @Override protected void onAttachedToWindow() {
    super.onAttachedToWindow();

    Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("https://api.themoviedb.org/3/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    movieService = retrofit.create(MovieService.class);

    Call<MovieResponse> nowPlayingMovies = movieService.getNowPlayingMovies(API_KEY);
    nowPlayingMovies.enqueue(new Callback<MovieResponse>() {
      @Override
      public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
        if (response.isSuccessful()) {
          MovieResponse movieResponse = response.body();
          nowPlayingAdapter.setData(movieResponse.results);
        }
      }

      @Override public void onFailure(Call<MovieResponse> call, Throwable t) {
        Log.e("C4Q", "Error obtaining movies", t);
        Toast.makeText(getContext(), "Error obtaining movies", Toast.LENGTH_SHORT).show();
      }
    });

    Call<MovieResponse> popularMovies = movieService.getPopularMovies(API_KEY);
    popularMovies.enqueue(new Callback<MovieResponse>() {
      @Override public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
        if (response.isSuccessful()) {
          MovieResponse movieResponse = response.body();
          mostPopularAdapter.setData(movieResponse.results);
        }
      }

      @Override public void onFailure(Call<MovieResponse> call, Throwable t) {
        Log.e("C4Q", "Error obtaining movies", t);
        Toast.makeText(getContext(), "Error obtaining movies", Toast.LENGTH_SHORT).show();
      }
    });
  }
}