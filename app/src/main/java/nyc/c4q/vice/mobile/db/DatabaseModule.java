package nyc.c4q.vice.mobile.db;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import nyc.c4q.vice.mobile.ui.App;

@Module
public class DatabaseModule {
  @Provides FavoritesDatabaseHelper provideDatabaseHelper(@App Context context) {
    return FavoritesDatabaseHelper.getInstance(context);
  }
}