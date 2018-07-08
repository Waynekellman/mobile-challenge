package nyc.c4q.vice.mobile;

import android.content.Context;
import javax.inject.Inject;
import nyc.c4q.vice.mobile.ui.App;

public class RealResourceManager implements ResourceManager {
  private final Context context;

  @Inject
  public RealResourceManager(@App Context context) {
    this.context = context;
  }

  @Override
  public String getString(int resourceId) {
    return context.getString(resourceId);
  }
}