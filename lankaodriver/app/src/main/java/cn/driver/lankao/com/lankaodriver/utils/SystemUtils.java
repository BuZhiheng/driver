package cn.driver.lankao.com.lankaodriver.utils;
import android.content.Context;
import android.net.ConnectivityManager;
import cn.driver.lankao.com.lankaodriver.application.LApplication;
/**
 * Created by buzhiheng on 2016/10/28.
 */
public class SystemUtils {
    public static boolean networkState(){
        ConnectivityManager cm = (ConnectivityManager) (LApplication.getCtx().getSystemService(Context.CONNECTIVITY_SERVICE));
        if (cm.getActiveNetworkInfo() != null){
            return cm.getActiveNetworkInfo().isAvailable();
            //连接成功返回true,否则返回false
        }
        return false;
    }
}