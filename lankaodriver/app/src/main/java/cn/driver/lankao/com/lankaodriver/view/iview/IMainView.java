package cn.driver.lankao.com.lankaodriver.view.iview;
import com.baidu.location.BDLocation;
/**
 * Created by buzhiheng on 2017/5/2.
 */
public interface IMainView {
    void showToast(String toast);
    void location();
    void showWorkOut();
    void showExit();
    void updateSuccess(BDLocation bdLocation);
}