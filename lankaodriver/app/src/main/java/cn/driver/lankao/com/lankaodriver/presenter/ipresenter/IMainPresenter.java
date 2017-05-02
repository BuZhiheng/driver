package cn.driver.lankao.com.lankaodriver.presenter.ipresenter;
import android.content.Context;
import com.baidu.location.BDLocation;
/**
 * Created by buzhiheng on 2017/5/2.
 */
public interface IMainPresenter {
    void checkPermission(Context context);
    void updateLoc(BDLocation bdLocation);
}