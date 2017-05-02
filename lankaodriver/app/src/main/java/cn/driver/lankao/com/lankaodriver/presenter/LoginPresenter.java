package cn.driver.lankao.com.lankaodriver.presenter;
import android.widget.EditText;
import java.util.ArrayList;
import java.util.List;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.driver.lankao.com.lankaodriver.model.CommonCode;
import cn.driver.lankao.com.lankaodriver.model.Driver;
import cn.driver.lankao.com.lankaodriver.presenter.ipresenter.ILoginPresenter;
import cn.driver.lankao.com.lankaodriver.utils.PrefUtil;
import cn.driver.lankao.com.lankaodriver.utils.SystemUtils;
import cn.driver.lankao.com.lankaodriver.utils.TextUtil;
import cn.driver.lankao.com.lankaodriver.view.iview.ILoginView;
/**
 * Created by buzhiheng on 2017/5/2.
 */
public class LoginPresenter implements ILoginPresenter {
    private ILoginView view;
    public LoginPresenter(ILoginView view){
        this.view = view;
    }
    @Override
    public void login(EditText phone, EditText psd) {
        if (!SystemUtils.networkState()){
            view.showToast(CommonCode.MSG_NETWORK_MISS);
            return;
        }
        String sphone = phone.getText().toString();
        String spsd = psd.getText().toString();
        if (TextUtil.isNull(sphone)){
            view.showToast("请输入手机号");
            return;
        }
        if (TextUtil.isNull(spsd)){
            view.showToast("请输入密码");
            return;
        }
        BmobQuery<Driver> query1 = new BmobQuery<>();
        query1.addWhereEqualTo("phone",sphone);

        BmobQuery<Driver> query2 = new BmobQuery<>();
        query2.addWhereEqualTo("password",spsd);

        List<BmobQuery<Driver>> queryList = new ArrayList<>();
        queryList.add(query1);
        queryList.add(query2);

        BmobQuery<Driver> query = new BmobQuery<>();
        query.and(queryList);
        query.findObjects(new FindListener<Driver>() {
            @Override
            public void done(List<Driver> list, BmobException e) {
                if (e == null){
                    if (list != null && list.size() > 0){
                        PrefUtil.putString(CommonCode.SP_USER_USERID,list.get(0).getObjectId());
                        view.toMain();
                        view.showToast("登录成功");
                    } else {
                        view.showToast("用户名或密码错误");
                    }
                } else {
                    view.showToast("登录失败");
                }
            }
        });
    }
}