package cn.driver.lankao.com.lankaodriver.presenter;
import android.Manifest;
import android.content.Context;
import android.os.Build;
import com.baidu.location.BDLocation;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.driver.lankao.com.lankaodriver.model.CommonCode;
import cn.driver.lankao.com.lankaodriver.model.Driver;
import cn.driver.lankao.com.lankaodriver.presenter.ipresenter.IMainPresenter;
import cn.driver.lankao.com.lankaodriver.utils.PermissionUtil;
import cn.driver.lankao.com.lankaodriver.utils.SystemUtils;
import cn.driver.lankao.com.lankaodriver.view.iview.IMainView;
public class MainPresenter implements IMainPresenter{
    private IMainView view;
    public MainPresenter(IMainView view){
        this.view = view;
    }
    @Override
    public void checkPermission(Context context) {
        if (!SystemUtils.networkState()){
            view.showToast(CommonCode.MSG_NETWORK_MISS);
            return;
        }
        String permission = Manifest.permission.ACCESS_FINE_LOCATION;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (PermissionUtil.checkNoPermission(context, permission)) {
//                String[] reqPer = new String[]{Manifest.permission.READ_PHONE_STATE,
//                        Manifest.permission.ACCESS_COARSE_LOCATION,
//                        Manifest.permission.ACCESS_FINE_LOCATION,
//                        Manifest.permission.READ_EXTERNAL_STORAGE,
//                        Manifest.permission.WRITE_EXTERNAL_STORAGE
//                };
//                view.requestPermission(reqPer);
                view.showToast(CommonCode.MSG_LOCATION_PERMISSION_MISS);
            } else {
                view.location();
            }
        } else {
            view.location();
        }
    }
    @Override
    public void updateLoc(final BDLocation bdLocation) {
        Driver driver = new Driver();
        driver.carLat = (float) bdLocation.getLatitude();
        driver.carLng = (float) bdLocation.getLongitude();
        driver.carType = CommonCode.CAR_SERVICE_TYPE_WORK;
        driver.update(Driver.getDriverId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
//                view.showToast("上传成功");
                    view.updateSuccess(bdLocation);
                } else {
                }
            }
        });
    }
    @Override
    public void setStopLoc() {
        Driver driver = new Driver();
        driver.carType = CommonCode.CAR_SERVICE_TYPE_WORK_OUT;
        driver.update(Driver.getDriverId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
                    view.showWorkOut();
                } else {
                    view.showToast("操作失败,请重试");
                }
            }
        });
    }
    @Override
    public void setExit() {
        Driver driver = new Driver();
        driver.carType = CommonCode.CAR_SERVICE_TYPE_WORK_OUT;
        driver.update(Driver.getDriverId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null){
                    view.showExit();
                } else {
                    view.showToast("退出失败,请重试");
                }
            }
        });
    }
}