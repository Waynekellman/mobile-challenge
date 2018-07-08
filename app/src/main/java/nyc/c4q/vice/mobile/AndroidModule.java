package nyc.c4q.vice.mobile;

import android.app.Application;
import android.content.Context;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import nyc.c4q.vice.mobile.ui.App;

@Module
public class AndroidModule {
  private Application application;

  public AndroidModule(Application application) {
    this.application = application;
  }

  @Provides @App Context provideAppContext() {
    return application;
  }

  @Provides @Singleton ResourceManager provideResourceManager(@App Context context) {
    return new RealResourceManager(context);
  }
}