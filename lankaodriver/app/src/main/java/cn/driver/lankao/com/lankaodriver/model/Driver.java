package cn.driver.lankao.com.lankaodriver.model;
import cn.bmob.v3.BmobObject;
import cn.driver.lankao.com.lankaodriver.utils.PrefUtil;
import cn.driver.lankao.com.lankaodriver.utils.TextUtil;
/**
 * Created by buzhiheng on 2017/5/2.
 */
public class Driver extends BmobObject {
    public String phone;
    public String driverName;
    public String carType;
    public Float carLat;
    public Float carLng;
    public static boolean isLogin(){
        String id = PrefUtil.getString(CommonCode.SP_USER_USERID,"");
        if (!TextUtil.isNull(id)){
            return true;
        }
        return false;
    }
    public static String getDriverId(){
        String id = PrefUtil.getString(CommonCode.SP_USER_USERID,"");
        return id;
    }
}