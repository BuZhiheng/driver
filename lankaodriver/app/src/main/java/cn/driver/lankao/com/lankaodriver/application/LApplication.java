package cn.driver.lankao.com.lankaodriver.application;
import android.app.Application;
import android.content.Context;
import com.baidu.mapapi.SDKInitializer;
import cn.bmob.v3.Bmob;
/**
 * Created by BuZhiheng on 2017/5/2.
 *
 */
public class LApplication extends Application {
    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        SDKInitializer.initialize(this);
        Bmob.initialize(this, "fe7893d2bc42ed427a178367a0e1d6b6");
    }
    public static Context getCtx(){
        return context;
    }
}