package cn.driver.lankao.com.lankaodriver.view.iview;
/**
 * Created by buzhiheng on 2017/5/2.
 */
public interface IMainView {
    void showToast(String toast);
    void requestPermission(String[] reqPer);
    void location();
}