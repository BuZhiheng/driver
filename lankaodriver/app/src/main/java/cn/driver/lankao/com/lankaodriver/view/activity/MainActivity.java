package cn.driver.lankao.com.lankaodriver.view.activity;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.baidu.location.BDLocation;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.driver.lankao.com.lankaodriver.R;
import cn.driver.lankao.com.lankaodriver.presenter.MainPresenter;
import cn.driver.lankao.com.lankaodriver.presenter.ipresenter.IMainPresenter;
import cn.driver.lankao.com.lankaodriver.utils.MyLocationClient;
import cn.driver.lankao.com.lankaodriver.utils.TimeUtil;
import cn.driver.lankao.com.lankaodriver.utils.ToastUtil;
import cn.driver.lankao.com.lankaodriver.view.iview.IMainView;
import cn.driver.lankao.com.lankaodriver.widget.MyDialog;
public class MainActivity extends AppCompatActivity implements IMainView {
    private IMainPresenter presenter;
    @Bind(R.id.tv_driver_address)
    TextView tvAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initView();
    }
    private void initView() {
        presenter = new MainPresenter(this);
        MyLocationClient.stopLoc();
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_driver_start:
                presenter.checkPermission(this);
                break;
            case R.id.btn_driver_stop:
                MyDialog.getAlertDialog(this, "温馨提示", "确定结束?", true, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyLocationClient.stopLoc();//先关闭定位
                        dialog.dismiss();
                        presenter.setStopLoc();//上传云端,设置carType为0
                    }
                });
                break;
            case R.id.btn_driver_exit:
                MyDialog.getAlertDialog(this, "提示", "确定退出软件?", true, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MyLocationClient.stopLoc();//先关闭定位
                        presenter.setExit();//上传云端,设置carType为0
                    }
                });
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        MyLocationClient.stopLoc();
    }
    @Override
    public void showToast(String toast) {
        ToastUtil.show(toast);
    }
    @Override
    public void location() {
        showToast("开启定位成功");
        MyLocationClient.loc(new MyLocationClient.MyLocationListener() {
            @Override
            public void onLocSuccess(BDLocation bdLocation) {
                presenter.updateLoc(bdLocation);
            }
        });
    }
    @Override
    public void showWorkOut() {
        tvAddress.setText("请点击开启定位...");
        showToast("操作成功");
    }
    @Override
    public void showExit() {
        finish();
    }
    @Override
    public void updateSuccess(BDLocation bdLocation) {
        tvAddress.setText("上次定位成功：\n\n" + TimeUtil.getTime(TimeUtil.FORMAT_YYMMDD_HMS) + "\n\n" + bdLocation.getAddrStr());
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            Intent home = new Intent(Intent.ACTION_MAIN);
//            home.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            home.addCategory(Intent.CATEGORY_HOME);
//            startActivity(home);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}