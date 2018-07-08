package nyc.c4q.vice.mobile.viewmodels;

public class MainViewModel {
  public MainViewModel(String toolbarTitle, int selectedTabLayoutId) {
    this.toolbarTitle = toolbarTitle;
    this.selectedTabLayoutId = selectedTabLayoutId;
  }

  public final String toolbarTitle;
  public final int selectedTabLayoutId;
}