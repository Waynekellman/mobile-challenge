package nyc.c4q.vice.mobile;

import dagger.Component;
import javax.inject.Singleton;
import nyc.c4q.vice.mobile.api.ApiModule;
import nyc.c4q.vice.mobile.ui.DetailsActivity;
import nyc.c4q.vice.mobile.ui.HomeView;

@Singleton
@Component(modules = ApiModule.class)
public interface ViceAppComponent {
  void inject(HomeView view);

  void inject(DetailsActivity activity);
}