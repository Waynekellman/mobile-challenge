package nyc.c4q.vice.mobile.api;

import nyc.c4q.vice.mobile.model.MovieDetails;
import nyc.c4q.vice.mobile.model.MovieResponse;
import nyc.c4q.vice.mobile.model.ReviewResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieService {
  @GET("movie/now_playing")
  Call<MovieResponse> getNowPlayingMovies(@Query("api_key") String key);

  @GET("movie/popular")
  Call<MovieResponse> getPopularMovies(@Query("api_key") String key);

  @GET("movie/{movie_id}")
  Call<MovieDetails> getMovieDetails(@Path("movie_id") int movieId, @Query("api_key") String key);

  @GET("movie/{movie_id}/reviews")
  Call<ReviewResponse> getReviews(@Path("movie_id") int movieId, @Query("api_key") String key);
}