package nyc.c4q.vice.mobile;

import dagger.Component;
import javax.inject.Singleton;
import nyc.c4q.vice.mobile.api.ApiModule;
import nyc.c4q.vice.mobile.db.DatabaseModule;
import nyc.c4q.vice.mobile.ui.DetailsActivity;
import nyc.c4q.vice.mobile.ui.FavoritesView;
import nyc.c4q.vice.mobile.ui.HomeView;
import nyc.c4q.vice.mobile.ui.MovieViewHolder;
import nyc.c4q.vice.mobile.ui.PicassoModule;

@Singleton
@Component(modules = {
    AndroidModule.class,
    ApiModule.class,
    DatabaseModule.class,
    PicassoModule.class
})
public interface ViceAppComponent {
  void inject(HomeView view);

  void inject(FavoritesView view);

  void inject(DetailsActivity activity);

  void inject(MovieViewHolder viewHolder);
}