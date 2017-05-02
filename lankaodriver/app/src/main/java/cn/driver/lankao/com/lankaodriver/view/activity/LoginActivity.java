package cn.driver.lankao.com.lankaodriver.view.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import butterknife.Bind;
import butterknife.ButterKnife;
import cn.driver.lankao.com.lankaodriver.R;
import cn.driver.lankao.com.lankaodriver.presenter.LoginPresenter;
import cn.driver.lankao.com.lankaodriver.presenter.ipresenter.ILoginPresenter;
import cn.driver.lankao.com.lankaodriver.utils.ToastUtil;
import cn.driver.lankao.com.lankaodriver.view.iview.ILoginView;
/**
 * Created by BuZhiheng on 2016/4/1.
 */
public class LoginActivity extends AppCompatActivity implements ILoginView {
    private ILoginPresenter presenter;
    @Bind(R.id.et_login_phone)
    EditText etPhone;
    @Bind(R.id.et_login_password)
    EditText etPsd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        presenter = new LoginPresenter(this);
    }
    public void onClick(View view){
        switch (view.getId()){
            case R.id.btn_login_login:
                presenter.login(etPhone,etPsd);
                break;
        }
    }
    @Override
    public void showToast(String toast) {
        ToastUtil.show(toast);
    }
    @Override
    public void toMain() {
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}