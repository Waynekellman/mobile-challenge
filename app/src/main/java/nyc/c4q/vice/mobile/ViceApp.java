package nyc.c4q.vice.mobile;

import android.app.Application;

public class ViceApp extends Application {

  private ViceAppComponent appComponent;

  @Override public void onCreate() {
    super.onCreate();

    appComponent = DaggerViceAppComponent.create();
  }

  public ViceAppComponent component() {
    return appComponent;
  }
}