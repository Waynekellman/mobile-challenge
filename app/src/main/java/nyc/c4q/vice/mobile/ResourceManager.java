package nyc.c4q.vice.mobile;

import android.content.Context;
import javax.inject.Inject;
import nyc.c4q.vice.mobile.ui.App;

public class ResourceManager {
  private final Context context;

  @Inject
  public ResourceManager(@App Context context) {
    this.context = context;
  }

  public String getString(int resourceId) {
    return context.getString(resourceId);
  }
}