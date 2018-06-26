package nyc.c4q.vice.mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import nyc.c4q.vice.mobile.R;

public class DetailsActivity extends AppCompatActivity {
  private ImageView imageView;
  private TextView titleView;
  private TextView releaseDateView;
  private TextView ratingView;
  private TextView overviewView;
  private ViewGroup reviews;
  private FloatingActionButton fab;

  @Override protected void onCreate(@Nullable Bundle bundle) {
    super.onCreate(bundle);
    setContentView(R.layout.activity_details);

    imageView = findViewById(R.id.image);
    titleView = findViewById(R.id.title);
    releaseDateView = findViewById(R.id.release_date);
    ratingView = findViewById(R.id.rating);
    overviewView = findViewById(R.id.overview);
    reviews = findViewById(R.id.reviews);
    fab = findViewById(R.id.fab);

    Intent intent = getIntent();
    int movieId = intent.getIntExtra("movie_id", 0);
    System.out.println("movieId: " + movieId);
  }
}