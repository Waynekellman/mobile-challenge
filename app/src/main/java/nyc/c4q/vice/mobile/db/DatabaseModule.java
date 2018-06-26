package nyc.c4q.vice.mobile.db;

import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import nyc.c4q.vice.mobile.ui.App;

@Module
public class DatabaseModule {
  @Provides @Singleton FavoritesDatabaseHelper provideDatabaseHelper(@App Context context) {
    return new FavoritesDatabaseHelper(context);
  }
}