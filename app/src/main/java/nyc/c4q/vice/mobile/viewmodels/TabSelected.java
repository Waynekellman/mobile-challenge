package nyc.c4q.vice.mobile.viewmodels;

public class TabSelected extends MainViewEvent {
  public final int menuItemId;

  public TabSelected(int menuItemId) {
    this.menuItemId = menuItemId;
  }
}